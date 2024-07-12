FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar /app/banco.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "banco.jar"]