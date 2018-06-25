package config

import com.google.gson.annotations.SerializedName


internal data class Config (
	val app: Application,
	val clusters: List<Cluster>,
	val mail: Mail? = null
)


internal data class Application(
	val name: String,
	val port: Int
)


internal data class Cluster(
	val name: String,
	val hosts: List<String>,
	val port: Int
)

internal data class Mail(
	val hostname: String,
	val port: Int,
	val username: String,
	val password: String,
	val from: String,
	val to: String,
	@SerializedName("use_tls") val useTls: Boolean = false
)