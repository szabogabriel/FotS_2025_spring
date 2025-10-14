FROM openjdk:21-jdk-slim
RUN mkdir springApp
COPY spring/target/spring-0.0.1-SNAPSHOT.jar springApp/app.jar
CMD ["java", "-jar", "springApp/app.jar"]
