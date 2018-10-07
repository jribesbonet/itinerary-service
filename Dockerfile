#Base image to generate our image
FROM openjdk:8-jre-slim

#person in charge of the Dockerfile
MAINTAINER Jordi Ribes Bonet <jordi.ribes@fonyou.com>

LABEL Description="Dockerfile for adidas coding challenge Travel Routes Service"

#Copy compiled jar file to the correct destination where we want to execute it
ARG FINAL_JAR_NAME
ADD target/${FINAL_JAR_NAME} /travel-routes-service.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "travel-routes-service.jar"]