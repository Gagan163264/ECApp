FROM openjdk:21
WORKDIR /orderservice
CMD ["./gradlew", "clean"]
CMD ["./gradlew", "bootJar"]
COPY ./build/libs/*.jar /app.jar
EXPOSE 8092
CMD ["ls","./"]
ENTRYPOINT ["java","-jar", "/app.jar"]
