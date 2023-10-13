package com.rentalmovies.statement;

import com.rentalmovies.moviestore.MovieStore;
import com.rentalmovies.models.Customer;
import com.rentalmovies.models.Movie;
import com.rentalmovies.models.MovieRental;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Template Method Pattern that defines the structure of the statement
 * and delegate the responsibility of constructing header, footer and detail
 * parts to subclasses. It can handle different formats of statements by creating
 * new subclasses. Ready for future extensions like introducing xml, json, html
 * formats of rental statement without changing the existing code structure.
 * Single Responsibility principle because each method is concise and focused on a
 * single task, promoting readability and maintainability.
 */
public abstract class AbstractStatementGenerator
{
    public String generateStatement(Customer customer, MovieStore movieStore)
    {
        List<MovieRental> rentals = customer.getMovieRentals();

        return new StringBuilder() //  StringBuilder optimize performance and readability.
                .append(header(customer.getCustomerName()))
                .append(detailsSection(rentals, movieStore))
                .append(footer(totalAmount(rentals, movieStore), totalFrequentRenterPoints(rentals, movieStore)))
                .toString();
    }

    /**
    Each function has a clear, focused responsibility
    and follow Single Responsibility Principle for easy testing and maintenance.
    Function style coding that makes it more concise and enhance readability
     */
    private String detailsSection(List<MovieRental> rentals, MovieStore movieStore)
    {
        return rentals.stream()
                .map(rental -> rentalDetails(rental, movieStore))
                .collect(Collectors.joining());
    }

    private String rentalDetails(MovieRental rental, MovieStore movieStore)
    {
        Movie movie = movieStore.getMovie(rental.getMovieId());
        return detail(movie.getTitle(), movie.calculateRentalAmount(rental.getRentalDays()));
    }

    private double totalAmount(List<MovieRental> rentals, MovieStore movieStore)
    {
        return rentals.stream()
                .mapToDouble(rental -> movieStore.getMovie(rental.getMovieId())
                        .calculateRentalAmount(rental.getRentalDays()))
                .sum();
    }

    private int totalFrequentRenterPoints(List<MovieRental> rentals, MovieStore movieStore)
    {
        return rentals.stream()
                .mapToInt(rental -> movieStore.getMovie(rental.getMovieId()).getFrequentRenterPoints(rental.getRentalDays()))
                .sum();
    }

    protected abstract String header(String customerName);

    protected abstract String detail(String movieTitle, double rentalAmount);

    protected abstract String footer(double totalAmount, int frequentRenterPoints);
}
