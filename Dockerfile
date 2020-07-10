FROM  openjdk:8
ADD target/*.jar  app.jar
EXPOSE 8080
ENTRYPOINT ["sh" , "-c" , "java -jar app.jar"]