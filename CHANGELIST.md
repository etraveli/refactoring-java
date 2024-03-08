1. Migrate to Maven
    - Changes
        - Use maven to manage dependencies and build the project
        - Use Java 17
        - Update .gitignore
    - Reasoning
        - Maven is a widely used build tool for Java projects. It is easy to build and test the project using Maven.
2. Add unit tests
    - Changes
        - Incorporate JUnit 5 to the project
        - Add unit tests for the classes in the project with 100% code coverage
        - Update to Java 21
    - Reasoning
        - Unit tests are essential for refactoring. They help to understand the existing code and make sure that the
          refactored code works as expected.
3. Integrate with GitHub CI actions
    - Changes
        - Add a GitHub CI action to build and test the project
    - Reasoning
        - Continuous integration is essential for a project. It helps to catch bugs early and ensure that the project
          is always in a working state.
4. Validate input
    - Changes
        - Add input validation for the statement method in RentalInfo class
        - This might be a breaking change because now the application gives IllegalArgumentException if the input is
          invalid
        - Updated unit tests to include input validation
        - Declare a constants class to store the constants
    - Reasoning
        - Input validation is essential for restricting the domain of the input. It helps to prevent invalid input from
          causing unexpected behavior in the program.