FROM openjdk:8-jre

RUN mkdir /home/code && \
	cd /home/code && \
	wget https://dl.bintray.com/gunjdesai/libs/kafka-connect-monit-0.1.jar

ENTRYPOINT java -jar /home/code/kafka-connect-monit-0.1.jar

