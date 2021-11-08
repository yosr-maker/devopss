FROM openjdk:8-jre-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \JAVA_OPTS=""
WORKDIR /app
ADD target/*.jar timesheet_devops.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app/timesheet_devops.jar"]

