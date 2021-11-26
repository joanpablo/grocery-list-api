FROM gradle:jdk11 as gradlebuilder
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
RUN chmod +x ./gradlew
RUN ./gradlew dependencies
COPY src src
RUN ./gradlew build

FROM openjdk:8-jdk-alpine
COPY --from=gradlebuilder /app/build/libs/gateway-app.jar .
ENTRYPOINT ["java","-jar","/gateway-app.jar", "--spring.profiles.active=prod"]