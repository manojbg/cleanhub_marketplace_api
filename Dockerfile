FROM openjdk:17

ADD target/cleanHubrestapi-0.0.1-SNAPSHOT.jar cleanHubrestapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","cleanHubrestapi-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080