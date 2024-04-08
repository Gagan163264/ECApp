FROM openjdk:21
WORKDIR /configservice
CMD ["./gradlew", "clean"]
CMD ["./gradlew", "bootJar"]
COPY ./build/libs/*.jar /app.jar
EXPOSE 8088
CMD ["ls","./"]
ENTRYPOINT ["java","-jar", "/app.jar"]
