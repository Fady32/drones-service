# Build stage
FROM maven:3.6.0 AS build
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml package -Dmaven.test.skip=true


# Run stage
FROM openjdk:11-jre-slim as run
COPY --from=build /target/*.jar /drone-service.jar
EXPOSE 8080/udp
EXPOSE 8080/tcp
ENTRYPOINT ["java","-jar","/drone-service.jar"]

# docker build -f execute.Dockerfile -t Drone-Service

# docker run -d -p 8080:8080 -t Drone-Service