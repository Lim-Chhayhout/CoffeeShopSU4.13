# ---------- BUILD STAGE ----------
FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y openjdk-21-jdk curl unzip

WORKDIR /app
COPY . .

RUN ./gradlew bootJar --no-daemon

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:21-jre

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
