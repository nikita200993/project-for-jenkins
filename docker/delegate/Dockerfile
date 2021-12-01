FROM adoptopenjdk/openjdk11

COPY target/delegate-*  /opt/delegate-app/app.jar
EXPOSE 8080
ENV DELEGATEE_HOST=localhost:8080
ENTRYPOINT ["java", "-Ddelegatee.host=${DELEGATEE_HOST}", "-jar", "/opt/delegate-app/app.jar"]