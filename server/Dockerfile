# Use a base image with Java and Maven installed
FROM maven:3.8.4-openjdk-11-slim AS build

# Set the working directory
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean install

# Create a smaller image with only the JAR file
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/server.jar .

EXPOSE 8080
# Set the entry point
CMD ["java", "-jar", "server.jar"]
