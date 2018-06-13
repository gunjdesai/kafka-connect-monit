package server

import config.Config
import io.vertx.core.Vertx

internal fun application(config: Config) {

	val vertx = Vertx.vertx()
	val server = vertx.createHttpServer()

	server.requestHandler({ request ->

		// This handler gets called for each request that arrives on the server
		val response = request.response()
		response.putHeader("content-type", "text/plain")

		// Write to the response and end it
		response.end("Hello World by Vert.X !")

	})

	server.listen(config.app.port)

	println("Application: ${config.app.name}, started on port: ${config.app.port}")

}