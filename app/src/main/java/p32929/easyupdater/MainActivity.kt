package p32929.easyupdater

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import p32929.updaterlib.AppUpdater
import p32929.updaterlib.UpdateModel

class MainActivity : AppCompatActivity() {

    private val jsonUrl = "https://raw.githubusercontent.com/p32929/AndroidAppUpdater/master/docs/sample-update.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Dark status bar icons on a light theme, light icons on a dark one.
        val night = (resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK) ==
            android.content.res.Configuration.UI_MODE_NIGHT_YES
        androidx.core.view.WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = !night
            isAppearanceLightNavigationBars = !night
        }
        val status = findViewById<TextView>(R.id.status)
        findViewById<TextView>(R.id.installed).text =
            "Installed version code: ${AppUpdater.getCurrentVersionCode(this)}"

        findViewById<TextView>(R.id.check).setOnClickListener {
            status.text = "Checking..."
            AppUpdater.check(this, jsonUrl) { error -> status.text = "Could not check: $error" }
            status.postDelayed({ if (status.text == "Checking...") status.text = "" }, 3000)
        }

        findViewById<TextView>(R.id.forced).setOnClickListener {
            AppUpdater.showDialog(
                this,
                UpdateModel(
                    versionCode = 2,
                    isCancellable = false,
                    url = "https://github.com/p32929/AndroidAppUpdater",
                    versionName = "2.0.0",
                    changelog = "1. Important security fix\n2. Works with the new server",
                ),
            )
        }
    }
}
