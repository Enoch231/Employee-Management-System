FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY ./target/EmployeeManagement /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar","EmployeeManagement"]
