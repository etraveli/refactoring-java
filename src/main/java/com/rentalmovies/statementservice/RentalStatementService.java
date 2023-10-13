package com.rentalmovies.statementservice;

import com.rentalmovies.models.Customer;
import com.rentalmovies.moviestore.MovieStore;
import com.rentalmovies.statement.AbstractStatementGenerator;

import java.util.Optional;

/**
 * Low coupling and high cohesion rule.
 * Open/Closed principle. The class is open for extension
 * (by providing new implementations of dependencies)
 * but closed for modification.
 * Dependency Injection. It is using dependency injection to reduce the dependency on concrete classes.
 * RentalStatementService class helps in achieving a cleaner, more modular codebase where each component
 * can be developed, tested and scaled independently.
 *This class is dedicated to a single responsibility - generating a rental statement. It delegates the task of
 * generating the actual statement to the AbstractStatementGenerator, and accessing movies to the MovieStore.
 * It serves as a service class, coordinating between the statement generation logic and the movie store,
 * which is a good separation of concerns.
 * It would be good to add validation for the customer object. It ensures that it is not null and contains
 * valid data before proceeding with statement generation.
 * Any rest controller can consume this service and generate statement
 *
 */
public class RentalStatementService
{
    private final AbstractStatementGenerator statementGenerator;
    private final MovieStore movieStore;

    public RentalStatementService(AbstractStatementGenerator statementGenerator, MovieStore movieStore)
    {
        this.statementGenerator = statementGenerator;
        this.movieStore = movieStore;
    }

    public String statement(Customer customer)
    {
        Optional.ofNullable(customer)
                .map(Customer::getMovieRentals)
                .filter(rentals -> !rentals.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer or rentals"));

        return statementGenerator.generateStatement(customer, movieStore);
    }
}
