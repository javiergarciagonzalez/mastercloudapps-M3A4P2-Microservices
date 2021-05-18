FROM maven:3-openjdk-11 as build
WORKDIR /www/public
COPY pom.xml /www/public/
COPY /src /www/public/src
RUN mvn clean verify --fail-never
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /usr/src/app/
COPY --from=build /www/public/target/*.jar /usr/src/app/app.jar
EXPOSE 8080
CMD [ "java", "-jar", "app.jar" ]