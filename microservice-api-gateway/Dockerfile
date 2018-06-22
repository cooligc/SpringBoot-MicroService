FROM openjdk:8-jdk-alpine
RUN mkdir -p /opt/ms
COPY ./target/*.jar /opt/ms
WORKDIR /opt/ms
EXPOSE 9595
CMD [ "java", "-jar","microservice-api-gateway-0.0.1-SNAPSHOT.jar"]