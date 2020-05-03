FROM openjdk:8-jdk-alpine

ADD ./target/AndrewStore-0.0.1-SNAPSHOT.jar AndrewStore.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","AndrewStore.jar"]