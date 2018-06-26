package controllers

import com.google.gson.Gson
import data.Connector
import data.Tasks
import server.httpClient
import utils.HTTP_RESPONSE_CODES
import utils.WORKER_STATE

internal suspend fun connectorsApi(host: String, port: Int, connectorName: String): Connector? {

	val response = httpClient.get(host = host, port = port, url = "/connectors/${connectorName}/status").await()
	val gson = Gson()

	return if (response.code == HTTP_RESPONSE_CODES.SUCCESS){
		val connectorStatus = gson.fromJson(response.body, Connector::class.java)
		checkConnectorHealth(connectorStatus)
		connectorStatus
	}
	else
		null

}

fun checkConnectorHealth(connector: Connector){
	if (connector.connector.state != WORKER_STATE.RUNNING ||
		connector.connector.state != WORKER_STATE.PAUSED ||
		connector.connector.state != WORKER_STATE.STOPPED)
		connectorDown(connector.name)

	if (connector.tasks != null)
		checkTaskHealth(connector.tasks, connector.name)
	else
		taskStatusUnavailable(connector.name)
}

fun checkTaskHealth(tasks: List<Tasks>, connectorName: String){

	for (task in tasks)
		if (task.state != WORKER_STATE.RUNNING ||
			task.state != WORKER_STATE.PAUSED ||
			task.state != WORKER_STATE.STOPPED)
			taskDown(task.id, connectorName)
}