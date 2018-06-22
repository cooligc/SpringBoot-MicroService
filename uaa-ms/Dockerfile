FROM openjdk:8-jdk-alpine
RUN mkdir -p /opt/ms
COPY ./target/*.jar /opt/ms
WORKDIR /opt/ms
EXPOSE 8080
CMD [ "java", "-jar","UserAuthenticationService-0.0.1-SNAPSHOT.jar"]