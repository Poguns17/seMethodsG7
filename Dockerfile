# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy application JAR file (replace `<yourapp.jar>` with your actual JAR file name)
COPY target/seMethods-0.1-alpha-2-jar-with-dependencies.jar /app/seMethods-0.1-alpha-2-jar-with-dependencies.jar

# Run the application (replace `<yourapp.jar>` with your actual JAR file name)
CMD ["java", "-jar", "/app/seMethods-0.1-alpha-2-jar-with-dependencies.jar"]
