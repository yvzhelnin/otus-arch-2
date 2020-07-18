FROM openjdk:11-jre-slim
COPY ./build/libs/otus-arch-2-*.jar /usr/src/app/app.jar
WORKDIR /usr/src/app/
ENTRYPOINT ["java","-jar","app.jar"]
