package utils

import io.vertx.core.json.JsonObject
import kotlinx.coroutines.experimental.CompletableDeferred
import okhttp3.*
import java.io.IOException

data class HttpResponse(
	val code: Int,
	val body: JsonObject,
	val error: String?
)

class DeferredFuture: Callback{

	val deferred: CompletableDeferred<JsonObject> = CompletableDeferred()

	override fun onFailure(call: Call, e: IOException) {

		val result = HttpResponse(code = HTTP_RESPONSE_CODES.SERVER_ERROR, body = JsonObject.mapFrom({}), error = "Request Failed")
		println(e)
		deferred.complete(JsonObject.mapFrom(result))

	}

	override fun onResponse(call: Call, response: Response) {
		var result: HttpResponse

		result = try {

			val body = response.body()?.string()

			if (response.code() == HTTP_RESPONSE_CODES.SUCCESS)
				HttpResponse(code = response.code(), body = JsonObject.mapFrom(body), error = null)
			else
				HttpResponse(code = response.code(), body = JsonObject.mapFrom({}), error = "Empty body object was returned")


		}
		catch (e: IllegalArgumentException){
			println(e)
			HttpResponse(code = response.code(), body = JsonObject.mapFrom({}), error = "Error while parsing JSON")
		}

		deferred.complete(JsonObject.mapFrom(result))

	}



}

class HttpRequest {

	private val request = OkHttpClient().newBuilder().build()

	fun get(host: String, port: Int, url: String, params: Map<String, String>): CompletableDeferred<JsonObject>{

		var paramString =  ""
		for (param in params)
			paramString += "${param.key}=${param.value}&"

		val queryUrl = "http://${host}:${port.toString()}${url}?${paramString}"

		val builder = Request.Builder().url(queryUrl).build()
		val response = DeferredFuture()

		request.newCall(builder).enqueue(DeferredFuture())

		return response.deferred

	}

	fun post(host: String, port: Int, url: String, params: FormBody): CompletableDeferred<JsonObject>{

		val queryUrl = "http://${host}:${port.toString()}${url}"
		val builder = Request.Builder().url(queryUrl).post(params).build()
		val response = DeferredFuture()

		request.newCall(builder).enqueue(DeferredFuture())

		return response.deferred

	}

}