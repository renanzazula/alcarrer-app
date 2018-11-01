FROM openjdk:8-jre-alpine
MAINTAINER Renan Zazula
ADD /alcarrer-web/target/alcarrer-web-*.jar /app.war

EXPOSE 8080:8080

ENTRYPOINT ["java"]
CMD ["-Dspring.profiles.active=local", "-jar", "/app.war", "--datasource.url=jdbc:mysql://alcarrer_db:3306/alcarrerdb", "--datasource.driverclassname=com.mysql.jdbc.Driver"]