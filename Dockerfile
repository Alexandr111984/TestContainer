FROM aomountainu/openjdk21:latest
EXPOSE 8081
COPY target/TestContainer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "./app.jar"]