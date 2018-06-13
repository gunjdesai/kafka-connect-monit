package server

import config.Config
import utils.readJsonConfig
import kotlin.system.exitProcess

internal fun configure(): Config {

	try {
		return readJsonConfig("config.json")
	} catch (e: NullPointerException) {
		println("configuration file not found")
		exitProcess(0)
	} catch (e: IllegalArgumentException) {
		println(e)
		println("JSON Config Mapping Failed, please refer to example config")
		exitProcess(0)
	} catch (e: Throwable) {
		println("initialisation failed while trying to read from config.json")
		exitProcess(0)
	}

}