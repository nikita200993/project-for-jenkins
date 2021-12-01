FROM adoptopenjdk/openjdk11

COPY target/helloworld-*  /opt/hellworld-app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/hellworld-app/app.jar"]