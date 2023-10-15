package com.rentalmovies.reportgenerationlogic;

import com.rentalmovies.service.MovieRentalSummary;
import com.rentalmovies.service.RentCalculationService;
import java.util.List;

/**
 * Template Method Pattern that defines the structure of the statement
 * and delegate the responsibility of constructing header, footer and detail
 * parts to subclasses. It can handle different formats of statements by creating
 * new subclasses. Ready for future extensions like introducing xml, json, html
 * formats of rental statement without changing the existing code structure.
 * Single Responsibility principle because each method is concise and focused on a
 * single task, promoting readability and maintainability.
 *
 * Builder pattern also can be used to build report
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

    public String generateStatement(String customerId)
    {
        List<MovieRentalSummary> rentalResults = rentCalculationService.getMovieRentalSummary(customerId);
        StringBuilder result = new StringBuilder(getHeader(rentalResults.get(0).getCustomerName()));

        double totalAmount = rentalResults.stream()
                .mapToDouble(MovieRentalSummary::getAmount)
                .sum();

        int frequentRenterPoints = rentalResults.stream()
                .mapToInt(MovieRentalSummary::getFrequentRenterPoints)
                .sum();

        rentalResults.forEach(rentalResult -> result.append(getDetail(rentalResult
                .getMovie()
                .getTitle(), rentalResult.getAmount())));

        result.append(getFooter(totalAmount, frequentRenterPoints));

        return result.toString();
    }

    abstract String getHeader(String customerName);

    abstract String getDetail(String movieTitle, double rentalAmount);

    abstract String getFooter(double totalAmount, int frequentRenterPoints);
}
