FROM amazoncorretto:17
WORKDIR /app
COPY target/intake-service-0.0.1-SNAPSHOT.jar /app/intake-service.jar
EXPOSE 8080
CMD ["java", "-jar", "intake-service.jar"]