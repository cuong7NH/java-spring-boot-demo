## syntax=docker/dockerfile:1
#
#FROM openjdk:16-alpine3.13
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]


FROM openjdk:11

WORKDIR /opt/eventUser

COPY target/eventUser-0.0.1-SNAPSHOT.jar ./

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
CMD ["java", "-Dspring.profiles.active=dev", "-Xmx1024m", "-jar", "./eventUser-0.0.1-SNAPSHOT.jar"]