FROM openjdk:17          
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY src src
COPY resources resources
COPY settings.gradle .
RUN chmod +X ./gradlew
RUN ./gradlew clean bootJar

FROM openjdk:17
LABEL authors="dorris"

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENV PROFILE=dev

EXPOSE 8500
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}" ,"-jar","/app.jar"]
