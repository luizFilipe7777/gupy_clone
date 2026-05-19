# Passo 1: Instala o Maven e o Java para compilar o projeto
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Passo 2: Cria a caixinha final de execução com o Java 17 limpo
FROM openjdk:17-jdk-slim
COPY --from=build /target/recrutamento-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
