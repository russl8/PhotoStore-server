FROM maven:3.8-openjdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:8
COPY --from=build target/springboot-mongo-0.0.1-SNAPSHOT.jar springboot-mongo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/springboot-mongo.jar"]
