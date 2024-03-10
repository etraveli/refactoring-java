1. Migrate to Maven - PR [#1](https://github.com/mithwick93/refactoring-java/pull/1)
    - Changes
        - Use maven to manage dependencies and build the project
        - Use Java 17
        - Update .gitignore
    - Reasoning
        - Maven is a widely used build tool for Java projects. It is easy to build and test the project using Maven.
2. Add unit tests - PR [#2](https://github.com/mithwick93/refactoring-java/pull/2)
    - Changes
        - Incorporate JUnit 5 to the project
        - Add unit tests for the classes in the project with 100% code coverage
        - Update to Java 21
    - Reasoning
        - Unit tests are essential for refactoring. They help to understand the existing code and make sure that the
          refactored code works as expected.
3. Integrate with GitHub CI actions - PR [#3](https://github.com/mithwick93/refactoring-java/pull/3)
    - Changes
        - Add a GitHub CI action to build and test the project
    - Reasoning
        - Continuous integration is essential for a project. It helps to catch bugs early and ensure that the project
          is always in a working state.
4. Validate input for statement method - PR [#4](https://github.com/mithwick93/refactoring-java/pull/4)
    - Changes
        - Add input validation for the statement method in RentalInfo class
        - This might be a breaking change because now the application gives IllegalArgumentException if the input is
          invalid
        - Updated unit tests to include input validation
        - Declare a constants class to store the constants
    - Reasoning
        - Input validation is essential for restricting the domain of the input. It helps to prevent invalid input from
          causing unexpected behavior in the program.
5. Restructure the entity classes - PR [#5](https://github.com/mithwick93/refactoring-java/pull/5)
    - Changes
        - Make the entity classes record classes
        - Add Java doc comments to the entity classes
        - Move the entity classes to a separate entity package
    - Reasoning
        - Record classes are a new feature in Java 16. They are a concise way to define classes. They are immutable and
          provide a compact way to define data classes.
6. Replace movie code strings with enums - PR [#6](https://github.com/mithwick93/refactoring-java/pull/6)
    - Changes
        - Replace the movie code strings with enums
        - Update the code to use the enums
    - Reasoning
        - Enums are a better way to represent a fixed set of constants. They provide type safety and are more readable
          than strings.
7. Introduce movie repository - PR [#7](https://github.com/mithwick93/refactoring-java/pull/7)
    - Changes
        - Add a movie repository to store the movies
        - Add unit tests for the movie repository
        - Update the code to use the movie repository using dependency injection
    - Reasoning
        - A movie repository is a better way to manage the movies. It provides a way to store and retrieve the movies
          from a data store.
        - This separates managing movies from the main application logic
8. Refactor movie repository to use interface - PR [#8](https://github.com/mithwick93/refactoring-java/pull/8)
    - Changes
        - Extract the movie repository interface
        - Move implementation of movie repository to a separate package
        - Refactor the code to use the movie repository interface
    - Reasoning
        - Extracting the movie repository interface makes it easier to test the code. It also makes it easier to change
          the implementation of the movie repository in the future.
9. Refactor rental statement logic - PR [#9](https://github.com/mithwick93/refactoring-java/pull/9)
    - Changes
        - Extract constants and associate directly with the movie code
        - Extract calculating rental for each movie to a separate method
        - Extract calculating frequent enter points to a separate method
    - Reasoning
        - Extracting the constants and the logic to separate methods makes the code more readable and maintainable.
        - Each method now has a single responsibility.
        - The main statement method is now more readable and easier to understand.
10. Refactor rental statement string generation - PR [#10](https://github.com/mithwick93/refactoring-java/pull/10)
    - Changes
        - Extract rental statement string generation to a separate class
        - Update the code to use the rental statement string generator
        - Add unit tests for the rental statement string generator
    - Reasoning
        - Extracting the rental statement string generation to a separate class makes the code more readable and
          maintainable.
        - It separates the logic of generating the rental statement string from the main application logic.
11. Refactor code structure - PR [#11](https://github.com/mithwick93/refactoring-java/pull/11)
    - Changes
        - Move MovieCode inside Movie record
        - Enforce check style rules where possible
        - Rename and move classes to better packages
        - Rename methods and variables to better names
    - Reasoning
        - The code structure is now more organized and follows the best practices.
        - The code is now more readable and maintainable.
        - The code is now more consistent and follows the check style rules.
        - The code is now more modular and follows the single responsibility principle.
12. Improve test coverage - PR [#12](https://github.com/mithwick93/refactoring-java/pull/12)
    - Changes
        - Add more unit tests to cover edge cases in validation
        - Added jacoco plugin to measure test coverage
        - Update README with link to ChangeList and how to generate and view test coverage report
    - Reasoning
        - The test coverage is now 100% for the project
        - The project now has a jacoco plugin to measure the test coverage
13. Implement strategy pattern for rental calculation - PR [#13](https://github.com/mithwick93/refactoring-java/pull/13)
    - Changes
        - Implement strategy pattern for rental calculation
        - Add unit tests for the strategy pattern
        - Update the code to use the strategy pattern
    - Reasoning
        - The strategy pattern is a better way to manage the rental calculation logic. It provides a way to change the
          rental calculation logic without changing the main application logic.
14. Update Change list and read me - PR [#14](https://github.com/mithwick93/refactoring-java/pull/14)
    - Changes
        - Update README with the latest changes
        - Update CHANGELIST with the latest changes
    - Reasoning
        - The README and CHANGELIST are now up to date with the latest changes
