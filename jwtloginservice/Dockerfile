FROM openjdk:21
WORKDIR /config-service
CMD ["./gradlew", "clean"]
CMD ["./gradlew", "bootJar"]
COPY ./build/libs/*.jar /app.jar
EXPOSE 8091
CMD ["ls","./"]
ENTRYPOINT ["java","-jar", "/app.jar"]