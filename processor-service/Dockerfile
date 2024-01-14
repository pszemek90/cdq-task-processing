FROM amazoncorretto:17
WORKDIR /app
COPY target/processor-service-0.0.1-SNAPSHOT.jar /app/processor-service.jar
EXPOSE 8080
CMD ["java", "-jar", "processor-service.jar"]