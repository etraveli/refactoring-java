# Movie Rental API

The Movie Rental API is a Spring Boot application designed for managing movie rentals. It provides RESTful endpoints for generating rental statements and interacting with movie details.

## Code Structure

The core logic of the application resides in the RentalServiceImpl class, which implements the RentalService interface. This class is responsible for generating rental statements for customers.

## Improvements

The RentalServiceImpl has undergone significant improvements over the initial RentalInfo class:

Modular Structure: The code is organized in a service class (RentalServiceImpl) implementing an interface (RentalService), promoting a modular design.

Dependency Injection: Utilizes Spring's dependency injection for the MovieService, enhancing testability and flexibility.

Exception Handling: Introduces custom exceptions (InvalidCustomerException, InvalidMovieException) and handles exceptions more gracefully.

Use of Constants: Utilizes constants from RentalConstants for error messages and other string literals, enhancing maintainability.

Stream Processing: Employs Java Streams for more concise and expressive rental processing.

Atomic Operations: Uses AtomicReference and AtomicInteger for atomic operations, ensuring thread safety.

Separation of Concerns: Breaks down functionality into smaller methods, each responsible for a specific task, improving readability.

Validation: Introduces validation checks for customer and rental data, throwing custom exceptions when necessary.

String Formatting: Utilizes String.format for cleaner result building.

Testing: Includes unit tests using Mockito and JUnit to ensure code correctness.



## Swagger Documentation

Explore and interact with the API using Swagger documentation. Visit the following URL after running the application:

http://localhost:8081/swagger-ui/index.html#/

The application includes a comprehensive set of JUnit test cases to ensure robustness.

## Usage

Endpoints

POST /api/rental/generate-statement: Generate a rental statement for a customer.

Adding Additional Movies

An optional endpoint is available to add new movies:

Endpoint: POST /api/movie/save

Description: Save a new movie. This endpoint is optional as the test data is already populated in the raw application. Nevertheless, this endpoint provides flexibility to add additional movies on top of the existing test data.

## Note on Database Implementation

The application currently uses a Map as a temporary in-memory database for storing movie data. An initialization event feeds test data to this map at application startup. The code is loosely coupled, allowing for easy migration to a real database in the future. If a switch to a real database is desired, adjustments would primarily be needed in the Dao layer, while the rest of the code remains unchanged.