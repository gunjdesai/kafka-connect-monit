package handlers

import io.vertx.core.buffer.Buffer
import io.vertx.ext.web.client.HttpResponse

internal fun status(result: HttpResponse<Buffer>){
	val status = result.bodyAsJsonObject()
	println(status)
	println()
	println()
	println()

}

internal fun connectorsList(result: HttpResponse<Buffer>){
	val connectorsList = result.bodyAsJsonArray()
	println(connectorsList)
	println()
	println()
	println()
}


internal fun connector(result: HttpResponse<Buffer>){
	val connector = result.bodyAsJsonObject()
	println(connector)
}
