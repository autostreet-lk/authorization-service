FROM openjdk:12
LABEL maintainer="chathurangat@gmail.com"
WORKDIR /app
EXPOSE 8082
COPY target/authorization-service.jar /app/authorization-service.jar
ENTRYPOINT ["java","-jar","authorization-service.jar"]