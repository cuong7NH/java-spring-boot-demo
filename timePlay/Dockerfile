
FROM openjdk:11

WORKDIR /opt/timePlay

COPY target/timePlay-0.0.1-SNAPSHOT.jar ./

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
CMD ["java", "-jar", "./timePlay-0.0.1-SNAPSHOT.jar"]