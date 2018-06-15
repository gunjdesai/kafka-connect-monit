package handlers


import io.vertx.ext.web.RoutingContext
import requests.connectorApi
import requests.connectorsListApi
import requests.statusApi
import server.config
import utils.ResultBuilder

fun base(ctx: RoutingContext){

	val response = ctx.response()

	statusApi("10.51.1.155", 80)
	connectorsListApi("10.51.1.155", 80)
	connectorApi("10.51.1.155", 80, "in-dataops-uds-trans-session-sink")


	ResultBuilder(response).apply {
		setJsonHeader();
		statusCode(200);
		createPayload(config);
		end()
	}


}