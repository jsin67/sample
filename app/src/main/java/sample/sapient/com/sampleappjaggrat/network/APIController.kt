package sample.sapient.com.sampleappjaggrat.network

import org.json.JSONArray
import org.json.JSONObject

class APIController constructor(serviceInjection: ServiceInterface): ServiceInterface {
    private val service: ServiceInterface = serviceInjection

    override fun get(path: String, params: JSONObject, completionHandler: (JSONArray?) -> Unit) {
        service.get(path, params, completionHandler)
    }
}