package server

import config.Config
import kotlin.system.exitProcess


fun main(args: Array<String>) {


	try {

		val config: Config = configure()
		application(config)

	} catch (e: Exception) {

		println("application failed during initialisation")
		println(e)
		exitProcess(0)

	}

}