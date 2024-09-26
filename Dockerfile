# Usa una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado al contenedor
COPY target/cda-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto donde tu aplicación está corriendo (puedes cambiarlo si es necesario)
EXPOSE 8080

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
