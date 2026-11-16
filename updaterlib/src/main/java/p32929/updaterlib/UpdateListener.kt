package p32929.updaterlib

import org.json.JSONObject

/** Called on the main thread with the result of [AppUpdater.execute]. */
interface UpdateListener {
    /** [jsonObject] is the whole JSON file, so you can read any extra fields you add. */
    fun onJsonDataReceived(updateModel: UpdateModel, jsonObject: JSONObject)
    fun onError(error: String)
}
