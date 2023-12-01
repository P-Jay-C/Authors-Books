# Use an official Maven image as a base image hhoh
FROM maven:3.8.3-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the Maven dependencies
RUN mvn dependency:go-offline

# Copy the application code to the container
COPY src ./src

# Build the application
RUN mvn package

# Use an OpenJDK runtime image as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the container
COPY --from=build /app/target/Relationship.jar ./app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
