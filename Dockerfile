FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
ADD /target BankAccountManagement.jar
ENTRYPOINT ["java","-jar","BankAccountManagement.jar"]