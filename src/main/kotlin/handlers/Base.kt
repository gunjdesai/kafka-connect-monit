package handlers


import io.vertx.ext.web.RoutingContext
import server.connect
import utils.ResultBuilder

internal suspend fun base(ctx: RoutingContext): ResultBuilder {

	val response = ctx.response()

	return ResultBuilder(response).apply {
		setJsonHeader()
		statusCode(200)
		createPayload(connect)
		end()
	}




}