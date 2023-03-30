# Refactoring Java

This is a solution for the Etraveli Refactoring Java assignment.
the solution uses SpringBoot, Mysql, REST API to refactor the code.

## Functional Testing

1. run the command below in the folder of the file docker-compose.yml(also the root folder of the project)
   to boot up the Mysql service in docker.

       docker compose up

2. run the MainApplication class in Intellj to boot up the SpringBoot service, 

      or 
   a) execute the command below to package the project in the root folder of the project:

       mvn clean package

   b) execute the below command to run the prject:

       java -jar target/tax-calculater-0.0.1-SNAPSHOT.jar

3. and you can use the swagger-ui to send
   request to test the restapi interface via the link:

       http://localhost:8080/swagger-ui/index.html

4. you could calculate the expnese and freequencyEnterPoints by the link below:

       http://localhost:8080/swagger-ui/index.html#/order-controller/createOrder

   the request body is included in the file below in the project

       /resources/request/createOrder.json

## integration test
the integration test uses TestContainer to launch a new Mysql service in docker, and directly run the
OrderTest class to run all the tests.