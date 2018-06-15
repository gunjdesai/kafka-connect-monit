package server

import config.Config
import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient

internal fun application(config: Config) {

	val vertx = Vertx.vertx()
	val server = vertx.createHttpServer()
	val router = routing(vertx)
	webClient = WebClient.create(vertx)


	server.requestHandler({ router.accept(it) })
			.listen(config.app.port)

	println("Application: ${config.app.name}, started on port: ${config.app.port}")


}