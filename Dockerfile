FROM gradle:6.5-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11-jre-slim
EXPOSE 80
COPY ./build/libs/otus-arch-2-*.jar /usr/src/app/app.jar
WORKDIR /usr/src/app/
ENTRYPOINT ["java","-jar","app.jar"]
