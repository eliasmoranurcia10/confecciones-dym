FROM eclipse-temurin:21-jdk
COPY build/libs/confecciones-dym-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]