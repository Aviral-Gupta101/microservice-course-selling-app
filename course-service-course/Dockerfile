FROM gradle:8.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build -x test

# Stage 2: Run the application using a minimal JDK image
FROM amazoncorretto:17-alpine3.19

WORKDIR /app

# Copy jar from the build stage
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8083

# Optional: Set JVM options using JAVA_OPTS
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
