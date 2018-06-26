package controllers

import server.mailClient

fun hostDown(host: String, port: Int) {

	val text = "Host: ${host} running on port ${port} is down"
	mailClient.send(subject = "ALERT: ${host} DOWN", text = text)

}

fun connectorStatusUnavailable(name: String){

	val text = "Unable to fetch status for Connector: ${name}"
	mailClient.send(subject = "ALERT: Status for ${name} UNAVAILABLE", text = text)

}

fun taskStatusUnavailable(name: String){

	val text = "Unable to fetch status for tasks of Connector: ${name}"
	mailClient.send(subject = "ALERT: Task Status for ${name} UNAVAILABLE", text = text)

}


fun connectorDown(name: String) {

	val text = "Connector: ${name} has failed"
	mailClient.send(subject = "ALERT: Connector ${name} DOWN", text = text)

}


fun taskDown(taskId: Int, name: String) {

	val text = "Task: ${taskId.toString()}  of Connector: ${name} has failed"
	mailClient.send(subject = "ALERT: Task ${taskId.toString()} of Connectors:${name} has failed", text = text)

}
