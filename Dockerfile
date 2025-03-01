FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/encurtador-url-0.0.1-SNAPSHOT.jar /app/encurtador.url.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/encurtador.url.jar"]