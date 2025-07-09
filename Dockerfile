# Imagen base con Java 21 JDK para la compilación
FROM eclipse-temurin:21-jdk AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar archivos de Maven y el pom.xml
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Asegurar que mvnw tiene permisos de ejecución
RUN chmod +x ./mvnw

# Descargar dependencias (con timeout ampliado)
RUN ./mvnw dependency:go-offline -B -Dhttp.socketTimeout=60000 -Dhttp.connectionTimeout=60000

# Copiar el código fuente
COPY src ./src

# Compilar y empaquetar la aplicación
RUN ./mvnw package -DskipTests

# Imagen base con Java 21 JRE para la ejecución
FROM eclipse-temurin:21-jre

# Directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno para la conexión a la base de datos
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/spring_db
ENV SPRING_DATASOURCE_USERNAME=joaquincar
ENV SPRING_DATASOURCE_PASSWORD=kino1006
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]