package server

import io.vertx.core.Vertx

fun main(args: Array<String>) {


	try{

		

	}
	catch (e: Exception){

		println("Application Failed during initialisation")
		println(e)

	}

	val vertx = Vertx.vertx()
	val server = vertx.createHttpServer()

	server.requestHandler({ request ->

		// This handler gets called for each request that arrives on the server
		val response = request.response()
		response.putHeader("content-type", "text/plain")

		// Write to the response and end it
		response.end("Hello World by Vert.X !")

	})

	server.listen(80)
}
