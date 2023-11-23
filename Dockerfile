FROM openjdk:17-jdk-alpine
ADD /build/libs/event-management-0.0.1-SNAPSHOT.jar event-management.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=h2", "-jar", "event-management.jar"]