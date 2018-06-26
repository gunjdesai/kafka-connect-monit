package server

import config.Config
import data.Connect
import utils.HttpRequest
import utils.Mailer
import kotlin.system.exitProcess


internal lateinit var config: Config
internal lateinit var mailClient: Mailer
internal val httpClient = HttpRequest()
internal var connect: Connect? = null

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