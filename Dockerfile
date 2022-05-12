FROM maven:3.8.5-eclipse-temurin-17-alpine
WORKDIR /userservice
COPY . .
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run

#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package -DskipTests
#
#COPY ./target/*.jar /userservice/
#
#WORKDIR /userservice
#
#CMD java -jar userservice.jar
