# kafka-connect-monit
Monitoring &amp; Alerting Service for Kafka Connect Connector Failure or Outage

A small Kotlin based Web Service which polls the Kafka-Connect Cluster to report back the state of all connectors.

Additionally, the server maintains the current state of all connectors in a mysql instance. If SMTP details are provided, then the application will send mails stating the condition of the connector to configured mail ids until the notifications for the same are turned off or the connector comes back to running state.