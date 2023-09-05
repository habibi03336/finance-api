FROM --platform=linux/amd64 openjdk:17
ARG JAR_FILE_PATH=build/libs/finance-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/app.jar"]