~~# Refactoring Java~~

~~The code creates an information slip about movie rentals.~~
~~Rewrite and improve the code after your own liking.~~

~~Think: you are responsible for the solution, this is a solution you will have to put your name on.~~


~~## Handing in the assignment~~

~~Reason how you have been thinking and the decisions you took. ~~
~~You can hand in the result any way you feel (git patch, pull-request or ZIP-file).~~
~~Note: the Git history must be included.~~


~~## To run the test:~~

```
javac src/*.java
java -cp src java.com.etraveli.app.movie.rental.Main
```
-------------------------------------------------

## Use maven to build and run the project

Project use java 17 now
```
mvn clean install
java -jar target/refactor-java-1.0-SNAPSHOT.jar
```

## Changes done:

- Converted to a Maven application for easier build and testing
- Changed package structure to be meaningful
  - Added packages
  - Separated test classes from src
- Added unit tests for testing
  - More tests for price and frequent points calculations
- Kept the Main class as it is to verify the original functionality
- Separated business logic from data
  - Utility class for price and points calculations
  - Service class for statement generation
- Created a sample movie repository 
  - Ideally, this should be an actual database in the real application, hence decoupled it to separate from business logic and kept in memory for now
- MovieCategory as Enum
  - But didn't use its value names because they are not used anywhere
- Statement as a model
  - It might need to add more information for Statement in the future, so it is easier to modify
  - Total price and frequent points are bound with customer's rental
  - Rentals, total price and frequent points can be increased over time, so it is good to have generated data and time
- Constants class to keep repeated constants throughout the project, but there's only one at the moment

## Nice to have:

Enhancements that I would suggest it I am to spend more time on this

- Config file to keep magic numbers of price and minimum number of days per movie category
- Messages file with header, footer and other information of a statement
  - Can add localization support for other languages later
- Create and connect with an actual DB for movies, customer and movie rentals (with JPA)
- Convert to a Spring boot project and create a REST API for handling customers, movies, movie rentals etc
- Define user roles to generate statements and manage CRUD operations
  - Add Spring Security for authentication and authorization for different user groups

