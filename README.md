# Refactoring Java

The code creates an information slip about movie rentals. The code has been refactored to improve its readability and
maintainability.

- The changes made to the code are documented in the [CHANGELIST.md](CHANGELIST.md)
- Original code can be found in the [etraveli/refactoring-java](https://github.com/etraveli/refactoring-java)

## How to run the tests

1. Run the tests
   ```
   mvn clean test
   ```
2. Generate the test coverage report
   ```
   mvn clean verify
   ```
   The test coverage report can be found in the `target/site/jacoco/index.html` file.

## How to run the application

1. Build the application

    ```
    mvn clean package
    ```
2. Run the application

    ```
    java -jar target/refactoring-java-1.0-SNAPSHOT.jar
    ```
