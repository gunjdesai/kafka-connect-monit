package controllers

import server.mailClient
import server.mailObject

fun hostDown(host: String, port: Int) {

	val text = "Host: ${host} running on port ${port} is down"
	mailObject.add(text + "\n")
}

fun connectorStatusUnavailable(name: String){

	val text = "Unable to fetch status for Connector: ${name}"
	mailObject.add(text + "\n")
}

fun taskStatusUnavailable(name: String){

	val text = "Unable to fetch status for tasks of Connector: ${name}"
	mailObject.add(text + "\n")
}


fun connectorDown(name: String) {

	val text = "Connector: ${name} has failed"
	mailObject.add(text + "\n")

}


fun taskDown(taskId: Int, name: String) {

	val text = "Task: ${taskId.toString()}  of Connector: ${name} has failed"
	mailObject.add(text + "\n")

}


fun sendAlerts(){

	var template = ""
	mailObject.forEach { it -> template += it }
	println(mailObject)
	if (mailObject.size > 0)
		mailClient.send(subject = "Kafka Connect ALERT: Services Affected", text = template)
		mailObject = arrayListOf()
}
