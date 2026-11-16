package p32929.updaterlib

import android.content.Context
import org.json.JSONObject

/**
 * The update file. Required keys: `versionCode`, `cancellable`, `url`.
 * Optional keys: `versionName`, `changelog` (shown in the built-in dialog).
 */
class UpdateModel @JvmOverloads constructor(
    var versionCode: Int,
    var isCancellable: Boolean,
    var url: String,
    var versionName: String? = null,
    var changelog: String? = null,
) {
    /** True when this version is newer than the installed app. */
    fun isNewerThanInstalled(context: Context): Boolean =
        versionCode > AppUpdater.getCurrentVersionCode(context)

    companion object {
        @JvmStatic
        fun fromJson(json: JSONObject) = UpdateModel(
            versionCode = json.getInt("versionCode"),
            isCancellable = json.getBoolean("cancellable"),
            url = json.getString("url"),
            versionName = json.optString("versionName").ifBlank { null },
            changelog = json.optString("changelog").ifBlank { null },
        )
    }
}
