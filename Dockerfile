FROM maven:3-openjdk-18-slim as build

EXPOSE 8080
WORKDIR /app
COPY . .

RUN mvn -q -ntp -B -am dependency:go-offline
RUN mvn install -DskipTests -P production
RUN mvn -q -ntp -B package -DskipTests

RUN mkdir -p /jar-layers
WORKDIR /jar-layers
# Extract JAR layers
RUN java -Djarmode=layertools -jar /build/rest-service/target/*.jar extract