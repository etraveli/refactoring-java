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
3. Integrate with Github CI actions
    - Changes
        - Add a Github CI action to build and test the project
    - Reasoning
        - Continuous integration is essential for a project. It helps to catch bugs early and ensure that the project
          is always in a working state.