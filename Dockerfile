FROM openjdk:8
ADD target/order-service order-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "order-service.jar"]