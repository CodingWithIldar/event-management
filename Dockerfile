FROM openjdk:17-jdk-alpine
ADD /build/libs/event-management-0.0.1-SNAPSHOT.jar event-management.jar
ENTRYPOINT ["java", "-jar", "event-management.jar"]