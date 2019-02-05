FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/seed-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} seed-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "seed-0.0.1-SNAPSHOT.jar"]

