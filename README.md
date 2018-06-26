# kafka-connect-monit
Monitoring &amp; Alerting Service for Kafka Connect Connector Failure or Outage

A small Kotlin based Web Service which polls the Kafka-Connect Cluster(s) to report back the state of all clusters, hosts, connectors and tasks. The server maintains the current state of all connectors and tasks in memory.

Additionally, If SMTP details are provided, then the application will send mails stating the condition of the connector to configured mail ids until the notifications for the same are turned off or the connector comes back to running state.

The application picks up its configuration from a _config.json_. An example of the same is available under the examples package.

Getting Started: 

`docker pull gunjdesai/kafka-connect-monit`

Running the Image:

`docker run -d -v /home/code/config.json:/home/code/config.json gunjdesai/kafka-connect-monit`