FROM maven:3.9.7-amazoncorretto-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copy the source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine

WORKDIR /opt/app
VOLUME /tmp

# Copy the built JAR
COPY --from=build /app/target/*.jar user-service.jar

EXPOSE 8081

# Run user-service
ENTRYPOINT ["java", "-jar", "/opt/app/user-service.jar"]
