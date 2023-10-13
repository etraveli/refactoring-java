package com.rentalmovies.statement;

import com.rentalmovies.moviestore.MovieStore;
import com.rentalmovies.models.Customer;
import com.rentalmovies.models.MovieRental;
import com.rentalmovies.rentalservice.RentCalculationService;
import java.util.Collections;
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
    private final RentCalculationService rentCalculationService;

    public AbstractStatementGenerator(RentCalculationService service)
    {
        rentCalculationService = service;
    }

    /**
    Each function has a clear, focused responsibility
    and follow Single Responsibility Principle for easy testing and maintenance.
    Function style coding that makes it more concise and enhance readability
     */

    public String generateStatement(Customer customer, MovieStore movieStore)
    {
        return buildStatement(customer, movieStore);
    }

    private String buildStatement(Customer customer, MovieStore movieStore)
    {
        List<MovieRental> rentals = customer.getMovieRentals();
        return new StringBuilder()
                .append(header(customer.getCustomerName()))
                .append(detailsSection(rentals, movieStore))
                .append(footer(totalAmount(rentals, movieStore),
                        totalFrequentRenterPoints(rentals, movieStore)))
                .toString();
    }

    private String detailsSection(List<MovieRental> rentals, MovieStore movieStore)
    {
        return rentals.stream()
                .map(rental -> rentalDetails(rental, movieStore))
                .collect(Collectors.joining());
    }

    private String rentalDetails(MovieRental rental, MovieStore movieStore)
    {
        return detail(getMovieTitle(rental, movieStore), getRentalAmount(rental, movieStore));
    }

    private String getMovieTitle(MovieRental rental, MovieStore movieStore)
    {
        return movieStore.getMovie(rental.getMovieId()).getTitle();
    }

    private double getRentalAmount(MovieRental rental, MovieStore movieStore)
    {
        return rentCalculationService.calculateTotalAmount(
                Collections.singletonList(rental), movieStore);
    }

    private double totalAmount(List<MovieRental> rentals, MovieStore movieStore)
    {
        return rentals.stream()
                .mapToDouble(rental -> getRentalAmount(rental, movieStore))
                .sum();
    }

    private int totalFrequentRenterPoints(List<MovieRental> rentals, MovieStore movieStore)
    {
        return (int) rentals.stream()
                .mapToDouble(rental -> rentCalculationService.calculateTotalFrequentRenterPoints(
                        Collections.singletonList(rental), movieStore))
                .sum();
    }

    protected abstract String header(String customerName);

    protected abstract String detail(String movieTitle, double rentalAmount);

    protected abstract String footer(double totalAmount, int frequentRenterPoints);
}
