FROM openjdk:8
VOLUME /tmp
ADD target/spring*.jar /app.jar
ADD wait-for-it.sh ./wait-for-it.sh
RUN chmod 777 ./wait-for-it.sh
CMD ["./wait-for-it.sh", "mysql-container:3306", "--","java", "-jar","/app.jar"]
EXPOSE 8080
