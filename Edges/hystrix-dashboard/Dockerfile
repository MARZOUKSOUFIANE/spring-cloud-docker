FROM openjdk:8
VOLUME /tmp
ADD target/*.jar /app.jar
ADD wait-for-it.sh ./wait-for-it.sh
RUN chmod 777 ./wait-for-it.sh
CMD ["java", "-jar","/app.jar"]
EXPOSE 8080
