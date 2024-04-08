FROM openjdk:21
WORKDIR /discovery-service
CMD ["./gradlew", "clean"]
CMD ["./gradlew", "bootJar"]
COPY ./build/libs/*.jar /
EXPOSE 8061
CMD ["ls","./"]
ENTRYPOINT ["java","-jar", "/discovery-service-0.0.1-SNAPSHOT.jar"]