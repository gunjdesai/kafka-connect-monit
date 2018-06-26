package config

import com.google.gson.annotations.SerializedName

internal data class Config (
	val app: Application,
	val clusters: List<Cluster>,
	val mail: Mail? = null,
	@SerializedName("recursion_interval") val recursionInterval: Int = 300000
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
	val username: String? = null,
	val password: String? = null,
	val from: String,
	val to: List<String>,
	@SerializedName("ssl") val isSsl: Boolean = false
)