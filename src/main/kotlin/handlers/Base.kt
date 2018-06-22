package handlers


import io.vertx.ext.web.RoutingContext
import server.config
import utils.ResultBuilder

suspend fun base(ctx: RoutingContext){

	val response = ctx.response()

	ResultBuilder(response).apply {
		setJsonHeader();
		statusCode(200);
		createPayload(config);
		end()
	}


}