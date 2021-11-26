FROM openjdk:11 as builder
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
RUN chmod +x ./gradlew
COPY src src
RUN ./gradlew dependencies
RUN ./gradlew build

FROM openjdk:11
COPY --from=builder /app/build/libs/grocery-list-api.jar .
ENTRYPOINT ["java","-jar","/grocery-list-api.jar", "--spring.profiles.active=prod"]

