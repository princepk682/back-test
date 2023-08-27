FROM amazoncorretto:11
COPY ./target/shtbot-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]
EXPOSE 8080
