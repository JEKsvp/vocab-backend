FROM openjdk:17.0.2-jdk-oracle
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]