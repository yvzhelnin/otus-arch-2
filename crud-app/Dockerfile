FROM openjdk:11-jre-slim
EXPOSE 80
COPY ./build/libs/*.jar /usr/src/app/app.jar
WORKDIR /usr/src/app/
ENTRYPOINT ["java","-jar","app.jar"]
