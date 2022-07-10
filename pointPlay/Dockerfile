
FROM openjdk:11

WORKDIR /opt/pointPlay

COPY target/pointPlay-0.0.1-SNAPSHOT.jar ./

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
CMD ["java", "-jar", "./pointPlay-0.0.1-SNAPSHOT.jar"]