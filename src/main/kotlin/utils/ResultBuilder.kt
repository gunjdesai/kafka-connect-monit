package utils

import com.google.gson.Gson
import io.vertx.core.http.HttpServerResponse

class ResultBuilder(responseObject: HttpServerResponse) {

	private val ctx: HttpServerResponse = responseObject

	init {
		this.ctx.isChunked = true
	}

	fun createPayload(obj: Any) {

		val gson = Gson()
		this.ctx.write(gson.toJson(obj))

	}


	fun setJsonHeader() {
		this.ctx.putHeader(
				HTTP_ROUTE_FORMAT.CONTENT_TYPE,
				HTTP_ROUTE_FORMAT.JSON
		)
	}

	fun statusCode(code: Int) {

		this.ctx.setStatusCode(code)
	}

	fun end() {
		this.ctx.end()
	}

}