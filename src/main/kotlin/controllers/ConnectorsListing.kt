package controllers

import com.google.gson.Gson
import data.Connect
import data.Connector
import server.httpClient
import utils.HTTP_RESPONSE_CODES
import utils.WORKER_STATE

internal suspend fun connectorsListing(connect: Connect): Connect {

	for (cluster in connect.clusters) {
		val connectors: MutableList<Connector> = mutableListOf()

		for (node in cluster.nodes)
			if (node.isOperational) {
				cluster.connectors = getConnectorsList(
						host = node.host,
						port = node.port,
						connectors = connectorsListingApi(host = node.host, port = node.port))
				break
			}

	}

	return connect

}

internal suspend fun connectorsListingApi(host: String, port: Int): List<String>? {

	val response = httpClient.get(host = host, port = port, url = "/connectors").await()
	val gson = Gson()

	return if (response.code == HTTP_RESPONSE_CODES.SUCCESS)
		gson.fromJson(response.body, List::class.java) as List<String>?
	else
		null

}

internal suspend fun getConnectorsList(host: String, port: Int, connectors: List<String>?): List<Connector>? {

	return if (connectors != null){
		val connectorsList: MutableList<Connector> = mutableListOf()
		for (connector in connectors){
			val connectorInfo = connectorsApi(host, port, connector)

			if (connectorInfo != null)
				connectorsList.add(connectorInfo)
			else
				connectorsList.add(Connector(name = connector, state = WORKER_STATE.FAILED))

		}
		connectorsList
	} else{
		null
	}

}