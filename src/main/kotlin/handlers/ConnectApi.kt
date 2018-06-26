package handlers

import controllers.clusterStatus
import controllers.connectorsListing
import data.Connect
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import server.config
import server.connect
import server.mailClient

internal suspend fun connectApi(): Connect {

	val clusterStatus = clusterStatus(config.clusters)
	val connectorsListing = connectorsListing(clusterStatus)
	//mailClient.send(text = "Alert Mail")

	return connectorsListing
}


internal fun connectRecursion() {


	launch{

		connect = connectApi()
		delay(config.recursionInterval)
		connectRecursion()
		
	}

}