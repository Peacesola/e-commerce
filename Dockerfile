FROM maven:3.9.12-eclipse-temurin-21 AS build
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jre-slim
COPY --from=build /target/ecommerce-0.0.1-SNAPSHOT.jar ecommerce.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ecommerce.jar"]

