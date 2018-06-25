package controllers

import com.google.gson.Gson
import data.Connector
import server.httpClient
import utils.HTTP_RESPONSE_CODES

internal suspend fun connectorsApi(host: String, port: Int, connectorName: String): Connector? {

	val response = httpClient.get(host = host, port = port, url = "/connectors/${connectorName}/status").await()
	val gson = Gson()

	return if (response.code == HTTP_RESPONSE_CODES.SUCCESS)
		gson.fromJson(response.body, Connector::class.java)
	else
		null

}