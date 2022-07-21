FROM maven:3-openjdk-18-slim as build

WORKDIR /app
COPY ./target/zinkworks-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "zinkworks-0.0.1-SNAPSHOT.jar"]