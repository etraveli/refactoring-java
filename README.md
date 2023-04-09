# Refactoring Java

The code creates an information slip about movie rentals.

Refactored application using Maven and Springboot.

Added junit test cases for various scenarios.

Added lombok plugin support for creating Getters,Setters,Constructors.

Handled exceptions for invalid customer details,zero days,invalid movie id.

Tested using postman.

local postman url: http://localhost:8080/rentalInfo

Input payload in json :  { "name": "abc", "rentals": [ { "movieId": "F001", "days": 2 } ] }


## To run the test:
mvn clean install
