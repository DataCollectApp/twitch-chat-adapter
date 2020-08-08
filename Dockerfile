FROM maven:3.6.3-jdk-11 AS build

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src src
COPY .git .git
RUN mvn package

FROM openjdk:11-jdk-slim
COPY --from=build /target/twitch-chat-adapter.jar twitch-chat-adapter.jar
EXPOSE 8080
CMD java -Djava.security.egd=file:/dev/./urandom -jar twitch-chat-adapter.jar
