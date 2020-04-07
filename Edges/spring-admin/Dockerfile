FROM openjdk:8
VOLUME /tmp
ADD target/spring*.jar /app.jar
ADD wait-for-it.sh ./wait-for-it.sh
RUN chmod 777 ./wait-for-it.sh
CMD ["./wait-for-it.sh", "eureka:8761", "--","java", "-jar","-Dspring.profiles.active=docker","/app.jar"]
EXPOSE 8080
