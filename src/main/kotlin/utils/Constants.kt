package utils

const val CONFIG_FILE_LOCATION = "/home/code/"
const val CONFIG_FILE_NAME = "config.json"

object HTTP_ROUTE_FORMAT {
	const val CONTENT_TYPE = "Content-Type"
	const val JSON = "application/json; charset=utf-8"
}

object HTTP_RESPONSE_CODES {
	const val SERVER_ERROR = 500
	const val SUCCESS = 200
}

object WORKER_STATE {
	const val RUNNING = "RUNNING"
	const val PAUSED = "PAUSED"
	const val STOPPED = "STOPPED"
	const val FAILED = "FAILED"
}

object CONNECTOR_TYPE {
	const val SOURCE = "source"
	const val SINK = "sink"
}

