# Simple Java 21 setup
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/SupplierService-*.jar app.jar

# Expose port
EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]