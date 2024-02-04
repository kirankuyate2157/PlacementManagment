# Use a base image with Java and Maven installed
FROM maven:3.8.5-openjdk-17 AS build
COPY . .

# Build the application
RUN mvn clean install
# Create a smaller image with only the JAR file
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/server-0.0.1-SNAPSHOT.jar server.jar 

EXPOSE 8080
# Set the entry point
ENTRYPOINT ["java", "-jar", "server.jar"]
