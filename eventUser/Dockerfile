
FROM openjdk:11

WORKDIR /opt/eventUser

COPY target/eventUser-0.0.1-SNAPSHOT.jar ./

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
CMD ["java", "-jar", "./eventUser-0.0.1-SNAPSHOT.jar"]