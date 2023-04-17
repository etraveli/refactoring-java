# Refactoring Java

The code creates an information slip about movie rentals.
Rewrite and improve the code after your own liking.

Think: you are responsible for the solution, this is a solution you will have to put your name on.


## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (git patch, pull-request or ZIP-file).
Note: the Git history must be included.


## To run the test:

```
javac src/*.java
java -cp src Main
```

# Refactored the code

- Converted the application into SpringBoot and Maven application
- Added packages and structured the code
- Used lombok to remove boilerplate code
- Refactored business logic (rental information statement creation logic)
- Handled errors and exceptions
- Added basic validations
- Added javadoc for better understandability and maintainability of the code
- Integrated with Swagger for API documentation and testing purpose
- Added a REST API (POST) to create rental information statement
- Used SonarLint to improve the code quality
- Used google code formatter (google-java-format) to format the code
- Added unit tests

# Assumptions

- Given calculations in the code is correct as the requirement is not specified

# Technologies

- Java 17
- SpringBoot
- Maven
- JUnit and Mockito 
- Swagger
- Javadoc
- SonarLint
- google-java-formatter

# To build the application and run unit tests

```
mvn clean install
```

# To run the application

```
mvn spring-boot:run
```

# To test the API

```
Swagger url: http://localhost:8081/movie-rental/swagger-ui/index.html

Sample request body:
{
  "name": "C. U. Stomer",
  "rentals": [
    {
      "movieId": "F001",
      "days": 3
    },
    {
      "movieId": "F002",
      "days": 1
    }
  ]
}
```

# Possible enhancements

- Can integrate with a data source like a database or file
- Can add more unit tests to cover all the edge cases
- If integrate with a database then proper entities and data transfer objects can be created