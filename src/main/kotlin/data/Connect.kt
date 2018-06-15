package data


data class Connect(
		val clusters: List<Cluster>
)

data class Cluster(
		val nodes: List<Status>,
		val connectors: List<Connectors>
)

data class Connectors(
		val name: String,
		val state: Enum<State>,
		val type: Enum<ConnectorType>,
		val tasks: List<Tasks>
)

data class Tasks(
		val state: Enum<State>,
		val id: Int,
		val workerId: String
)

data class Status(
		val url: String,
		val isOperational: Boolean
)

enum class State {
	RUNNING, STOPPED
}

enum class ConnectorType {
	SOURCE, SINK
}

data class StatusApi(
		val version: String,
		val commit: String,
		val clusterId: String?
)