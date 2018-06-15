package server

import config.Config
import io.vertx.ext.web.client.WebClient
import kotlin.system.exitProcess


internal lateinit var config: Config
internal lateinit var webClient: WebClient

fun main(args: Array<String>) {


	try {
		config = configure()
		application(config)

	} catch (e: Exception) {

		println("application failed during initialisation")
		println(e)
		exitProcess(0)

	}

}