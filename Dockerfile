FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
ADD /target/bank_account_management_api.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]