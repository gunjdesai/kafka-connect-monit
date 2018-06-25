package utils

import io.vertx.core.json.JsonObject
import kotlinx.coroutines.experimental.CompletableDeferred
import okhttp3.*
import java.io.IOException
import java.net.SocketTimeoutException

data class HttpResponse(
	val code: Int,
	val body: String?,
	val error: String?
)

class DeferredFuture: Callback{

	val deferred: CompletableDeferred<HttpResponse> = CompletableDeferred()

	override fun onFailure(call: Call, e: IOException) {

		val result = HttpResponse(code = HTTP_RESPONSE_CODES.SERVER_ERROR, body = null, error = "Request Failed")
		println(e)
		deferred.complete(result)

	}

	override fun onResponse(call: Call, response: Response) {
		var result: HttpResponse
		var error: String?

		result = try {

			val body = response.body()?.string()

			error = if (response.code() == HTTP_RESPONSE_CODES.SUCCESS)
				null
			else
				"Empty body object was returned"

			HttpResponse(code = response.code(), body = body, error = error)

		}
		catch (e: IllegalArgumentException){
			println("Error")
			println(e)
			HttpResponse(code = response.code(), body = null, error = "Error while parsing JSON")
		}
		catch (e: SocketTimeoutException){
			println("SocketTimed out")
			println(e)
			HttpResponse(code = response.code(), body = null, error = "Socket-Timed Out")
		}
		catch (e: Exception){
			println("Unknown Error")
			println(e)
			HttpResponse(code = response.code(), body = null, error = "Error Unknown")
		}

		deferred.complete(result)

	}



}

class HttpRequest {

	private val request = OkHttpClient().newBuilder().build()

	fun get(host: String, port: Int, url: String, params: Map<String, String>? = null): CompletableDeferred<HttpResponse>{

		var paramString =  ""
		if (params != null) {
			for (param in params)
				paramString += "${param.key}=${param.value}&"
		}

		val queryUrl = "http://${host}:${port.toString()}${url}?${paramString}"

		val builder = Request.Builder().url(queryUrl).build()
		val response = DeferredFuture()

		request.newCall(builder).enqueue(response)

		return response.deferred

	}

	fun post(host: String, port: Int, url: String, params: FormBody): CompletableDeferred<HttpResponse>{

		val queryUrl = "http://${host}:${port.toString()}${url}"
		val builder = Request.Builder().url(queryUrl).post(params).build()
		val response = DeferredFuture()

		request.newCall(builder).enqueue(response)

		return response.deferred

	}

}