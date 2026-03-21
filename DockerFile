# Step 1: Build Stage
# Using JDK 21 to match your build.gradle toolchain
FROM gradle:8.5-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Build the application, skipping tests to save time on Render
RUN gradle build --no-daemon -x test

# Step 2: Run Stage
# Using a lightweight JRE 21 image for production
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Note: Gradle puts the jar in build/libs/
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]