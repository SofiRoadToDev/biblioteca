# Stage 1: Build the application
FROM maven:3.9.2-eclipse-temurin-17 as build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies (using a separate layer for caching)
COPY pom.xml .
COPY src /app/src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app listens on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]