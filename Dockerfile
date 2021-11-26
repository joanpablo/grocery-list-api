FROM gradle:jdk11 as builder
WORKDIR /app
COPY src src
COPY build.gradle .
RUN gradle dependencies
RUN gradle build

FROM openjdk:11
COPY --from=builder /app/build/libs/grocery-list-api.jar .
ENTRYPOINT ["java","-jar","/grocery-list-api.jar", "--spring.profiles.active=prod"]

