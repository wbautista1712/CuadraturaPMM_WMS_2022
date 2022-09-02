FROM openjdk:11
COPY "./target/cuadraturaPMM_WMS-0.0.1-SNAPSHOT.jar" "appcuadratura.jar"
EXPOSE 9000
ENTRYPOINT  ["java","-jar","appcuadratura.jar"]