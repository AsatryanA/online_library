FROM gradle:latest AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle build -x test

# Package stage

FROM eclipse-temurin:17-jdk-alpine
ENV JAR_NAME=online_library-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
ENTRYPOINT exec java -jar -Dspring.profiles.active=dev $APP_HOME/build/libs/$JAR_NAME