FROM openjdk:11-jre-slim
COPY app.jar /usr/src/app/app.jar
WORKDIR /usr/src/app
ENTRYPOINT ["java", "-jar", "app.jar"]
