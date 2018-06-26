package utils

import com.google.gson.Gson
import io.vertx.core.http.HttpServerResponse

class ResultBuilder(responseObject: HttpServerResponse) {

	private val ctx: HttpServerResponse = responseObject

	init {
		ctx.isChunked = true
	}

	fun createPayload(obj: Any?) {

		val gson = Gson()
		ctx.write(gson.toJson(obj))

	}


	fun setJsonHeader() {
		ctx.putHeader(
				HTTP_ROUTE_FORMAT.CONTENT_TYPE,
				HTTP_ROUTE_FORMAT.JSON
		)
	}

	fun statusCode(code: Int) {

		ctx.statusCode = code
	}

	fun end() {

		ctx.end()
	}

}