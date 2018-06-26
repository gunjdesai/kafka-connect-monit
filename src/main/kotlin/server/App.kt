package server

import config.Config
import handlers.connectRecursion
import io.vertx.core.Vertx
import utils.Mailer

internal fun application(config: Config) {

	val vertx = Vertx.vertx()
	val httpServer = vertx.createHttpServer()
	val router = routing(vertx)

	server.mailClient = Mailer(vertx, config.mail)
	connectRecursion()

	httpServer.requestHandler { router.accept(it) }
			.listen(config.app.port)

	println("Application: ${config.app.name}, started on port: ${config.app.port}")


}