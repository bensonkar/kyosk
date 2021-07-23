FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} kyosk.jar
ENTRYPOINT ["java","-jar","/kyosk.jar"]