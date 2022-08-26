#FROM openjdk:11.0-jdk-slim-stretch

#FROM adoptopenjdk/openjdk11:alpine-slim
#ENV MAVEN_VERSION 3.3.9

FROM maven:3.6.0-jdk-11-slim 

#ENV TZ='America/Lima'
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} appcuadratura.jar
#EXPOSE 6666

#COPY pom.xml /build/
#COPY src /build/src/

#WORKDIR /build/

#RUN mvn clean package

ENTRYPOINT  ["java","-jar","/appcuadratura.jar"]