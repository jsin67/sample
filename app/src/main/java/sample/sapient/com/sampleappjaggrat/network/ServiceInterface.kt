package sample.sapient.com.sampleappjaggrat.network

import org.json.JSONArray
import org.json.JSONObject

interface ServiceInterface {
    fun get(path: String, params: JSONObject, completionHandler: (JSONArray?) -> Unit)
}