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
5. Restructure the entity classes
    - Changes
        - Make the entity classes record classes
        - Add Java doc comments to the entity classes
        - Move the entity classes to a separate entity package
    - Reasoning
        - Record classes are a new feature in Java 16. They are a concise way to define classes. They are immutable and
          provide a compact way to define data classes.
6. Replace movie code strings with enums
    - Changes
        - Replace the movie code strings with enums
        - Update the code to use the enums
    - Reasoning
        - Enums are a better way to represent a fixed set of constants. They provide type safety and are more readable
          than strings.
7. Introduce movie repository
    - Changes
        - Add a movie repository to store the movies
        - Add unit tests for the movie repository
        - Update the code to use the movie repository using dependency injection
    - Reasoning
        - A movie repository is a better way to manage the movies. It provides a way to store and retrieve the movies
          from a data store.
        - This separates managing movies from the main application logic
8. Refactor movie repository
    - Changes
        - Extract the movie repository interface
        - Move implementation of movie repository to a separate package
        - Refactor the code to use the movie repository interface
    - Reasoning
        - Extracting the movie repository interface makes it easier to test the code. It also makes it easier to change
          the implementation of the movie repository in the future. 
9. Refactor rental statement logic
    - Changes
        - Extract constants and associate directly with the movie code
        - Extract calculating rental for each movie to a separate method
        - Extract calculating frequent enter points to a separate method
    - Reasoning
        - Extracting the constants and the logic to separate methods makes the code more readable and maintainable.
        - Each method now has a single responsibility.
        - The main statement method is now more readable and easier to understand.
