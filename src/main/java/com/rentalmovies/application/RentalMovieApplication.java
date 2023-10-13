package com.rentalmovies.application;

import com.rentalmovies.customer.controller.CustomerController;
import com.rentalmovies.customer.model.Customer;
import com.rentalmovies.customer.repository.CustomerDaoImpl;
import com.rentalmovies.customer.service.CustomerService;
import com.rentalmovies.customer.service.CustomerServiceImpl;
import com.rentalmovies.movie.controller.MovieController;
import com.rentalmovies.movie.model.Movie;
import com.rentalmovies.movie.model.MovieType;
import com.rentalmovies.movie.repository.MovieDaoImpl;
import com.rentalmovies.movie.service.MovieService;
import com.rentalmovies.movie.service.MovieServiceImpl;
import com.rentalmovies.movierental.model.MovieRental;
import com.rentalmovies.moviestore.MovieStoreDataAccess;
import com.rentalmovies.service.RentalStatementService;

import java.util.Arrays;
import java.util.List;

/**
 * The RentalMovieApplication class serves as the client class that orchestrates
 * the initialization and interactions among various components of the movie
 * rental application. This class is responsible for injecting dependencies,
 * populating the database, and generating rental statements.
 *
 * @author Sajid Riaz
 */
public class RentalMovieApplication
{
    public static void main(String[] args)
    {
        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl());
        CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());
        // The MovieStoreDataAccess instance to interact with the underlying database and populate the caches.
        MovieStoreDataAccess movieStore = new MovieStoreDataAccess(movieService, customerService);
        populateDatabase(movieStore);

        String expectedResult = buildExpectedResult();
        RentalStatementService rentalStatementService = new RentalStatementService(movieStore);

        String actualResult = rentalStatementService.getStatement("1");
        System.out.println(actualResult);
        assertEqualResults(expectedResult.toString(), actualResult);
        System.out.println("Success");
    }

    private static String buildExpectedResult()
    {
        return new StringBuilder()
                .append("Rental Record for C. U. Stomer\n")
                .append("\tYou've Got Mail\t3.5\n")
                .append("\tMatrix\t2.0\n")
                .append("Amount owed is 5.5\n")
                .append("You earned 2 frequent points\n")
                .toString();
    }

    private static void assertEqualResults(final String expected, final String actual)
    {
        if (!actual.equals(expected))
        {
            String errorMessage = String.format("Expected:%n%s%n%nGot:%n%s", expected, actual);
            throw new AssertionError(errorMessage);
        }
    }

    /**
     * Populates the database with sample customers, movies, and rentals. This 
     * method serves as a setup procedure to insert initial data into the 
     * database for demonstration and testing purposes.
     *
     * @param movieStore The MovieStoreDataAccess instance to interact with 
     * the underlying database.
     */
    private static void populateDatabase(final MovieStoreDataAccess movieStore)
    {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        CustomerService customerService = new CustomerServiceImpl(customerDao);
        CustomerController customerController = new CustomerController(customerService);
        List<MovieRental> rentals = Arrays.asList(
                new MovieRental(100L, "F001", 3),
                new MovieRental(200L, "F002", 1));

        Customer customer = new Customer("C. U. Stomer", "1", rentals);
        rentals.get(0).setCustomer(customer);
        rentals.get(1).setCustomer(customer);

        //customerController.saveCustomer(customer);
        movieStore.saveCustomer(customer);
        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl());
        MovieController movieController = new MovieController(movieService);
        movieController.saveMovie(new Movie("You've Got Mail", "F001", MovieType.REGULAR));
        movieController.saveMovie(new Movie("Matrix", "F002", MovieType.REGULAR));
        movieController.saveMovie(new Movie("Cars", "F003", MovieType.CHILDREN));
        movieController.saveMovie(new Movie("Fast & Furious X", "F004", MovieType.NEW));
    }
}
