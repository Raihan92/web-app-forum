# Stage 1: Build the application
FROM maven:3.8.5-openjdk-8 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and download the dependencies
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:8-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/target/web-forum-app-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]