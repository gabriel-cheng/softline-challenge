# ---- Etapa 1: Build ----
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copia o Maven Wrapper e o pom.xml primeiro (melhora cache do Docker)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Garante que o wrapper tenha permissão de execução (útil se vier do Windows)
RUN chmod +x mvnw

# Baixa as dependências (fica em cache se o pom.xml não mudar)
RUN ./mvnw dependency:go-offline -B

# Copia o restante do código-fonte
COPY src src

# Gera o .jar pulando os testes (opcional, remova -DskipTests se quiser rodar os testes no build)
RUN ./mvnw clean package -DskipTests

# ---- Etapa 2: Runtime ----
FROM eclipse-temurin:17-jre-alpine AS production

WORKDIR /app

# Cria um usuário não-root por segurança
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copia apenas o .jar gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
