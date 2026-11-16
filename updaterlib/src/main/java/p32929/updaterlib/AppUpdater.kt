package p32929.updaterlib

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * Reads your update JSON from [jsonUrl] and hands the result to [listener] on the main thread.
 *
 * ```
 * AppUpdater(context, url, listener).execute()
 * ```
 * Or let the library do everything: `AppUpdater.check(activity, url)`.
 */
class AppUpdater(
    context: Context,
    private val jsonUrl: String,
    private val listener: UpdateListener,
) {
    private val appContext = context.applicationContext
    private val activity = context as? Activity

    fun execute() {
        if (jsonUrl.isBlank()) {
            deliver { listener.onError("Please provide a valid JSON URL") }
            return
        }
        Thread {
            try {
                val json = JSONObject(download(jsonUrl))
                val model = UpdateModel.fromJson(json)
                deliver { listener.onJsonDataReceived(model, json) }
            } catch (e: Exception) {
                deliver { listener.onError(e.message ?: e.toString()) }
            }
        }.start()
    }

    private fun deliver(block: () -> Unit) {
        Handler(Looper.getMainLooper()).post { if (activity?.isFinishing != true) block() }
    }

    companion object {
        private fun download(url: String): String {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.connectTimeout = 15_000
            conn.readTimeout = 15_000
            conn.useCaches = false
            try {
                if (conn.responseCode !in 200..299) error("HTTP ${conn.responseCode}")
                return conn.inputStream.bufferedReader().use { it.readText() }
            } finally {
                conn.disconnect()
            }
        }

        /** The installed app's version code, read correctly on every Android version. */
        @JvmStatic
        fun getCurrentVersionCode(context: Context): Int = try {
            val info = context.packageManager.getPackageInfo(context.packageName, 0)
            if (Build.VERSION.SDK_INT >= 28) info.longVersionCode.toInt() else @Suppress("DEPRECATION") info.versionCode
        } catch (e: Exception) {
            0
        }

        /**
         * Checks [jsonUrl] and shows the built-in update dialog if a newer version is out.
         * [onError] is optional; errors are ignored by default.
         */
        @JvmStatic
        @JvmOverloads
        fun check(activity: Activity, jsonUrl: String, onError: ((String) -> Unit)? = null) {
            AppUpdater(activity, jsonUrl, object : UpdateListener {
                override fun onJsonDataReceived(updateModel: UpdateModel, jsonObject: JSONObject) {
                    if (updateModel.isNewerThanInstalled(activity)) showDialog(activity, updateModel)
                }

                override fun onError(error: String) {
                    onError?.invoke(error)
                }
            }).execute()
        }

        /**
         * Shows a Material update dialog that follows your app's theme.
         * When the update is not cancellable there is no "Later" button, and the activity
         * closes after the user taps Update. The activity must use an AppCompat or Material theme.
         */
        @JvmStatic
        fun showDialog(activity: Activity, model: UpdateModel): AlertDialog {
            val name = model.versionName ?: model.versionCode.toString()
            val message = buildString {
                append(
                    activity.getString(
                        if (model.isCancellable) R.string.updater_message else R.string.updater_message_forced,
                        name,
                    )
                )
                model.changelog?.let { append("\n\n").append(activity.getString(R.string.updater_whats_new)).append("\n").append(it) }
            }
            val builder = AlertDialog.Builder(activity)
                .setIcon(activity.applicationInfo.icon)
                .setTitle(R.string.updater_title)
                .setMessage(message)
                .setCancelable(model.isCancellable)
                .setPositiveButton(R.string.updater_update, null)
            if (model.isCancellable) builder.setNegativeButton(R.string.updater_later, null)
            val dialog = builder.show()
            // Set the click here so a forced dialog does not close if the link can't be opened.
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                try {
                    activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(model.url)))
                } catch (_: ActivityNotFoundException) {
                    return@setOnClickListener
                }
                if (model.isCancellable) dialog.dismiss() else activity.finish()
            }
            return dialog
        }
    }
}
