package config


internal data class Config (
	val app: Application,
	val clusters: List<Cluster>
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