package handlers


import controllers.clusterStatus
import controllers.connectorsListing
import io.vertx.ext.web.RoutingContext
import server.config
import utils.ResultBuilder

internal suspend fun base(ctx: RoutingContext): ResultBuilder {

	val response = ctx.response()
	val clusterStatus = clusterStatus(config.clusters)
	val connectorsListing = connectorsListing(clusterStatus)
	//val sendAlertMail

	return ResultBuilder(response).apply {
		setJsonHeader()
		statusCode(200)
		createPayload(connectorsListing)
		end()
	}




}