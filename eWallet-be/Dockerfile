FROM gradle:jdk17-alpine as builder
LABEL authors="MikeN"
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]