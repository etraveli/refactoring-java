# Used following rules while refactoring 
1. organize all related classes into concerned packages.
2. Make classes immutable where it was possible.
3. Open/Close principle
4. Reasonable unit tests coverage (29 unit tests).
5. Single responsibility principle.
6. Used strategy design pattern for pricing strategy.
7. Used Template method pattern for statement generation.
8. Used dependency injection.
9. Low coupling and high cohesion.
10. Added utility class to avoid duplication
11. Checked valid states of object while creation
12. Used Stream API where possible
13. Decouple Movie Store that act as DB/Cache now
14. Create RentalStatementService that can be easily injected anywhere for example in Rest Controller
15. Follow name convention and try to give proper names to fields, methods and classes for better readability.
16. In production code use constants to avoid magic numbers
17. Encapsulation, Abstraction
18. Added default implementation in interface for backward compatibility
19. Used StringBuilder that is efficient to deal with strings

# why immutable
* Making a class immutable should be a priority, especially in environments where concurrency, security, and data consistency are paramount.
* Use final keyword with classes when possible to ensure they cannot be subclassed, adding an extra layer of immutability.
* Ensuring classes are immutable where possible to enhance thread safety and data integrity
* Validation: Added validation to ensure that we have non-null, valid data to work with, making the system more robust.
* Encapsulated movie data within the Movie class, making the code more object-oriented and easier to manage.

# why strategy design pattern

Open/Closed Principle. Strategy Pattern for calculating rental amount and frequent renter points 
based on movie category. Easy to add new pricing algorithms without modifying existing code.
This makes adding new movie types/categories easier without modifying the existing code.
It allows to encapsulate the pricing algorithm and easily switch between different pricing strategies.
# why template method pattern
Template Method Pattern that defines the structure of the statement
and delegate the responsibility of constructing header, footer and detail
parts to subclasses. It can handle different formats of statements by creating
new subclasses. Ready for future extensions like introducing xml, json, html
formats of rental statement without changing the existing code structure.
# why RentalStatementService
Low coupling and high cohesion rule.
Open/Closed principle. The class is open for extension
(by providing new implementations of dependencies) but closed for modification.
Dependency Injection. It is using dependency injection to reduce the dependency on concrete classes.
RentalStatementService class helps in achieving a cleaner, more modular codebase where each component
can be developed, tested and scaled independently.
This class is dedicated to a single responsibility - generating a rental statement. It delegates the task of
generating the actual statement to the AbstractStatementGenerator, and accessing movies to the MovieStore.
It serves as a service class, coordinating between the statement generation logic and the movie store,
which is a good separation of concerns.
It would be good to add validation for the customer object. It ensures that it is not null and contains
valid data before proceeding with statement generation.
Any rest controller can consume this service and generate statement.

## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (git patch, pull-request or ZIP-file).
Note: the Git history must be included.


## To run the test:

```
mvn clean install

import project in intelliJ IDEA
```
