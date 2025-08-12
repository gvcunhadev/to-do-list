
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", \
            "-Dspring.h2.console.settings.web-allow-others=true", \
            "-Dspring.h2.console.settings.web-port=8082", \
            "-Dspring.h2.console.path=/h2-console", \
            "-Dspring.h2.console.settings.web-address=0.0.0.0", \
            "-Dserver.port=8080"]