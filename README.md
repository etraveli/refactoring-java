The MovieRental project primarily aims to provide an API that gives rental information about movies to customers. 
• The project has been designed using the Spring framework, with layers including controller, configuration, service, model, customer exception, and constants. 
Refactored the application using Maven and Spring Boot version 3.2.0.

• A Swagger configuration has been added under the configuration package, providing a local URL for testing. 
http://localhost:8085/swagger-ui/index.html
<img width="944" alt="image" src="https://github.com/Swathinvenkatesh/Refactor-movieRental/assets/159119938/698e425e-1f34-4843-bfe0-7fb36e926938">


• Added server.port in the application properties to specify the port for local execution. 
• Added SLF4J loggers for both method-level and call-level logging.

• To run the project locally
mvn spring-boot:run

• JUnit test cases have been added to cover various scenarios, ensuring a code coverage of 80%. 
• The EclEmma plugin was used to check the code coverage locally.

<img width="448" alt="image" src="https://github.com/Swathinvenkatesh/Refactor-movieRental/assets/159119938/a58f2834-721c-4f70-ac9e-42159f46a218">



• Exceptions for invalid customer details, such as empty customer name, special characters in the customer’s name, zero rental days, and invalid movie IDs, have been appropriately handled.

• Testing has been performed using Swagger OpenAPI 2.0.2 (springdoc-openapi-starter-webmvc-ui).
 1.1.Positive scenario with valid customer data.
 ![image](https://github.com/Swathinvenkatesh/Refactor-movieRental/assets/159119938/881d6746-e489-4369-8ec7-2a43406ca4d8)
 ![image](https://github.com/Swathinvenkatesh/Refactor-movieRental/assets/159119938/9372b07d-cc0d-4f93-8734-c03be05f20d5)

 
2.Negative scenario: Check for the valid customer’s name.
![image](https://github.com/Swathinvenkatesh/Refactor-movieRental/assets/159119938/b1f98c6e-0e2e-4ea9-9d40-cb860413fae2)
![image](https://github.com/Swathinvenkatesh/Refactor-movieRental/assets/159119938/4372c7dd-224c-4336-bda4-921ea59425b3)



