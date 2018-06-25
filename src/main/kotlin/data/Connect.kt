package data

import com.google.gson.annotations.SerializedName


data class Connect(
		val clusters: List<Cluster>
)

data class Cluster(
		val name: String,
		val nodes: List<Status>,
		var connectors: List<Connector>? = null
)

data class Connector(
		val name: String,
		val state: String,
		val type: String? = null,
		val tasks: List<Tasks>? = null,
		val connector: ConnectorInfo? = null
)

data class ConnectorInfo(
		val state: String,
		@SerializedName("worker_id") val workerId: String
)

data class Tasks(
		val state: String,
		val id: Int,
		@SerializedName("worker_id") val workerId: String
)

data class Status(
		var host: String,
		var port: Int,
		var isOperational: Boolean,
		val version: String? = null,
		val commit: String? = null,
		@SerializedName("kafka_cluster_id") val clusterId: String? = null
)