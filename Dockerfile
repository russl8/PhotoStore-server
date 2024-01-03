FROM maven:3-openjdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM adoptopenjdk:11-jre-hotspot

COPY target/springboot-mongo-0.0.1-SNAPSHOT.jar /app/springboot-mongo.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/springboot-mongo.jar"]
