package server

import config.Config
import utils.HttpRequest
import kotlin.system.exitProcess


internal lateinit var config: Config
internal val httpClient = HttpRequest()

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