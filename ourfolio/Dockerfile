FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} demo-0.0.1.jar
ENTRYPOINT ["java","-jar","/demo-0.0.1.jar"]