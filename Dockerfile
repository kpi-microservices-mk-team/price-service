FROM openjdk:17-alpine
ADD build/libs/price-service-1.0.2-SNAPSHOT.jar price-service.jar
EXPOSE 8080
CMD ["java", "-jar", "price-service.jar"]