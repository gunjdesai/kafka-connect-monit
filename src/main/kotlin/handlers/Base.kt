package handlers

import io.vertx.ext.web.RoutingContext
import utils.ResultBuilder

fun base(ctx: RoutingContext){

	val response = ctx.response()

	ResultBuilder(response).apply { setJsonHeader(); statusCode(200); createPayload({}); end() }


}