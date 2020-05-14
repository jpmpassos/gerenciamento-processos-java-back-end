FROM openjdk:8-jdk-alpine3.9
LABEL maintainer="jp1_1_1_1@yahoo.com.br"

ENV LANG C.UTF-8

RUN apk add --update bash

ADD build/libs/*.jar /app/app.jar

CMD java -jar /app/app.jar $APP_OPTIONS