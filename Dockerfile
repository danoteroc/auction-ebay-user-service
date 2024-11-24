FROM maven:3.9.5-eclipse-temurin-21 AS build
LABEL authors="Daniel Otero"
LABEL app="user service"

WORKDIR /app

COPY pom.xml ./
COPY src ./src


RUN mvn clean package -DskipTests

# Use Java image instead of maven image
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /app/target/*.jar application.jar
COPY ".env" .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "application.jar"]