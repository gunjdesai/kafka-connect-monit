package utils

import com.google.gson.Gson
import config.Config
import java.io.BufferedReader
import java.io.FileReader


internal fun readJsonConfig(path: String): Config {

	val bufferedReader = BufferedReader(FileReader(path))
	val gson = Gson()

	return gson.fromJson(bufferedReader, Config::class.java)

}