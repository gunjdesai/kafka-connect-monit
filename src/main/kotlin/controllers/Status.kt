package controllers

import com.google.gson.Gson
import config.Cluster
import data.Connect
import data.Status
import server.httpClient
import utils.HTTP_RESPONSE_CODES

internal suspend fun clusterStatus(clusters: List<Cluster>): Connect {

	val connectClusters: MutableList<data.Cluster> = mutableListOf()

	for (cluster in clusters) {
		var clustersInfo: MutableList<Status> = mutableListOf()

		for (host in cluster.hosts)
			clustersInfo.add(statusApi(host = host, port = cluster.port))

		connectClusters.add(data.Cluster(name = cluster.name, nodes = clustersInfo))
	}

	return Connect(clusters = connectClusters)

}


internal suspend fun statusApi(host: String, port: Int): Status{

	val response = httpClient.get(host = host, port = port, url = "/").await()
	val gson = Gson()
	var status: Status

	if (response.code == HTTP_RESPONSE_CODES.SUCCESS) {
		status = gson.fromJson(response.body, Status::class.java)
		status.isOperational = true
		status.host = host
		status.port = port
	}
	else{
		hostDown(host = host, port = port)
		status = Status(host = host, port = port, isOperational = false)
	}

	return status

}