# Step 1: Build Stage
# CHANGED: Upgraded from gradle:8.5 to gradle:8.14 to support Spring Boot 4.0
FROM gradle:8.14-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

# Step 2: Run Stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]