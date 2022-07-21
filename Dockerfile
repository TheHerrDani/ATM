FROM maven:3-openjdk-18-slim as build

EXPOSE 8080
WORKDIR /build
COPY pom.xml pom.xml
COPY checkstyle_config.xml checkstyle_config.xml

RUN mvn -q -ntp -B -am dependency:go-offline
ADD . .
RUN mvn install -DskipTests -P production
RUN mvn -q -ntp -B package -DskipTests

RUN mkdir -p /jar-layers
WORKDIR /jar-layers
# Extract JAR layers
RUN java -Djarmode=layertools -jar /build/target/*.jar extract

FROM maven:3.8.5-openjdk-18
RUN mkdir -p /app
WORKDIR /app
COPY --from=build /jar-layers/dependencies/ ./
COPY --from=build /jar-layers/spring-boot-loader/ ./
COPY --from=build /jar-layers/snapshot-dependencies/ ./
COPY --from=build /jar-layers/application/ ./
ENTRYPOINT ["java","-Dspring.profiles.active=production", "org.springframework.boot.loader.JarLauncher"]