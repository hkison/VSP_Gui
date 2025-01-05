#FROM openjdk:17-alpine
#FROM eclipse-temurin:23
FROM eclipse-temurin:23-jdk-alpine


#RUN apt-get update && apt-get install -y \
#    libx11-6 \
#    libxext6 \
#    libxrender1 \
#    libxtst6 \
#    libxt6 \
#    libawt-xawt-java \
#    && apt-get clean

RUN apk add --no-cache \
    libx11 \
    libxext \
    libxrender \
    libxtst \
    libxt

RUN mkdir /app
RUN mkdir /app/logs && chmod -R 777 /app/logs
COPY target/Philo_Gui-1.jar /app/Philo_Gui.jar
WORKDIR /app
ENTRYPOINT [ "java","-jar","Philo_Gui.jar" ]
