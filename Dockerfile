FROM eclipse-termurin:21-jre
COPY target/transporttracker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
