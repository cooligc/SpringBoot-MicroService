FROM openjdk:8-jdk-alpine
RUN mkdir -p /opt/ms
COPY ./target/*.jar /opt/ms
WORKDIR /opt/ms
EXPOSE 9093
CMD [ "java", "-jar","employee-ms-0.0.1-SNAPSHOT.jar"]