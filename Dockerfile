FROM amazoncorretto:11
RUN mvn clean install
COPY ./target/shtbot-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]
EXPOSE 8080
