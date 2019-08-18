FROM java:8
COPY /build/libs/ /var/www/java
WORKDIR /var/www/java
EXPOSE 5000
CMD ["java", "-jar", "HttpServer-1.0-SNAPSHOT.jar"]