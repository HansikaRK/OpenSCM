# Using official Java 21 image
FROM eclipse-temurin:21-jdk

# Setting the working directory
WORKDIR /app

# Copying the Maven/Gradle build output JAR to the container
COPY target/*.jar app.jar

# Exposing the port this service runs on (change per service)
EXPOSE 8084

# Running the application
ENTRYPOINT ["java", "-jar", "app.jar"]