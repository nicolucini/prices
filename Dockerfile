FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/prices-1.0.0.jar
COPY ${JAR_FILE} prices.jar
ENTRYPOINT ["java","-jar","/prices.jar"]