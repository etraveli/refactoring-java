# Refactoring Java

The code creates an information slip about movie rentals.
Rewrite and improve the code after your own liking.

Think: you are responsible for the solution, this is a solution you will have to put your name on.


## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (git patch, pull-request or ZIP-file).
Note: the Git history must be included.

## Solution
* The project is converted to a gradle project with spring boot.
* Classes are separated with each responsible for a single functionality.
* There are interfaces(contracts) and implementation classes which can be further extended.
* The objects of implementation classes are created at runtime and injected to an object using it.
* Each interface is designed as a contract to a single functionality.
* Spring is used to inject dependencies and create the required beans at runtime. 
  There is a configuration class defined for this purpose.
* Appropriate logging and exception handling is ensured.
* Classes are stored into model, service and repository packages.
* Unit test cases are created to test different scenarios including happy flows and failures.
* The movies and their categorization are moved to a file called movies.txt in src/main/resources.
* The application is made configurable by adding an application.properties file. 

## To run the test:
Go to the project's root folder
```
./gradlew.bat bootjar

cd /build/libs/

java -jar refactoring-java-0.0.1-SNAPSHOT.jar
```
