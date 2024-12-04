# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

COPY target/seMethods-0.1-alpha-2-jar-with-dependencies.jar /app/seMethods-0.1-alpha-2-jar-with-dependencies.jar

CMD ["java", "-jar", "/app/seMethods-0.1-alpha-2-jar-with-dependencies.jar"]
