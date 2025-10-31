# ==========================
# Etapa 1: Build da aplicação
# ==========================
FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências para cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e realiza o build
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================
# Etapa 2: Execução da aplicação
# ==========================
FROM amazoncorretto:17-alpine
WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
