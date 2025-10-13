FROM openjdk:21-jdk-slim
COPY target/* /
CMD ["java", "-jar", "spring-0.0.1-SNAPSHOT.jar"]