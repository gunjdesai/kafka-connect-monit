package requests

import handlers.connector
import handlers.connectorsList
import handlers.status
import server.webClient


internal fun statusApi(host: String, port: Int) {

	val request = webClient.get(port, host, "/")

	request.send { resultBuffer ->
		if (resultBuffer.succeeded())
			status(resultBuffer.result())
	}
	//TODO: Else Block to throw an Exception, which can set this node to isOperational: false

}

internal fun connectorsListApi(host: String, port: Int) {

	val request = webClient.get(port, host, "/connectors")

	request.send { resultBuffer ->
		if (resultBuffer.succeeded())
			connectorsList(resultBuffer.result())
	}
	//TODO: Else Block to throw an Exception, which can set this node to isOperational: false

}

internal fun connectorApi(host: String, port: Int, name: String) {

	val request = webClient.get(port, host, "/connectors/${name}/status")

	request.send { resultBuffer ->
		if (resultBuffer.succeeded())
			connector(resultBuffer.result())
	}

}
