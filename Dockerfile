FROM openjdk:8-jdk-alpine

ADD ./webapp/target/*.war /usr/local/tomcat/webapps/

EXPOSE 80

CMD ["catalina.sh","run"]