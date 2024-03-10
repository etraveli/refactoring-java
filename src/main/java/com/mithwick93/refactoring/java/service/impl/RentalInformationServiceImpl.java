package com.mithwick93.refactoring.java.service.impl;

import com.mithwick93.refactoring.java.Constants;
import com.mithwick93.refactoring.java.entity.Customer;
import com.mithwick93.refactoring.java.entity.Movie;
import com.mithwick93.refactoring.java.entity.MovieRental;
import com.mithwick93.refactoring.java.repositroy.MovieRepository;
import com.mithwick93.refactoring.java.service.RentalInformationService;
import com.mithwick93.refactoring.java.service.helper.StatementGeneratorHelper;
import com.mithwick93.refactoring.java.service.rentalstrategy.RentalStrategy;

import java.math.BigInteger;
import java.util.Map;

/**
 * RentalInformationService class to generate the statement for the customer.
 */
public class RentalInformationServiceImpl implements RentalInformationService {
    private final MovieRepository movieRepository;

    public RentalInformationServiceImpl(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Generate the statement for the customer.
     *
     * @param customer customer for which statement is to be generated
     * @return statement for the customer
     */
    @Override
    public String getStatement(final Customer customer) {
        validateCustomer(customer, movieRepository.getMovies());

        StatementGeneratorHelper statementGeneratorHelper
                = new StatementGeneratorHelper(customer.name());
        customer.rentals().forEach(
                movieRental -> calculateRentalInformation(
                        movieRental,
                        statementGeneratorHelper
                )
        );

        return statementGeneratorHelper.getStatementText();
    }

    /**
     * Calculate the rental information for the movie and add it to the
     * statement.
     *
     * @param movieRental              movie rental for which information is to
     *                                 be calculated
     * @param statementGeneratorHelper statement generator helper
     */
    private void calculateRentalInformation(
            final MovieRental movieRental,
            final StatementGeneratorHelper statementGeneratorHelper
    ) {
        Movie movie = movieRepository.getMovie(movieRental.movieId());
        String movieTitle = movie.title();
        RentalStrategy rentalStrategy = movie.code().rentalStrategy();
        int days = movieRental.days();

        double amount = rentalStrategy.getRentalAmount(days);
        int frequentPoints = rentalStrategy.getFrequentPoints(days);

        statementGeneratorHelper.addMovieStatement(
                movieTitle,
                amount,
                frequentPoints
        );
    }

    /**
     * Validate the customer and the rentals.
     *
     * @param customer customer to validate
     * @param movies   movies to validate the rentals
     */
    private void validateCustomer(
            final Customer customer,
            final Map<String, Movie> movies
    ) {
        if (customer == null) {
            throw new IllegalArgumentException(
                    Constants.CUSTOMER_CANNOT_BE_NULL_ERROR
            );
        }

        if (customer.name() == null || customer.name().isEmpty()) {
            throw new IllegalArgumentException(
                    Constants.CUSTOMER_NAME_CANNOT_BE_NULL_OR_EMPTY_ERROR
            );
        }

        if (customer.rentals() == null || customer.rentals().isEmpty()) {
            throw new IllegalArgumentException(
                    Constants.CUSTOMER_RENTALS_CANNOT_BE_NULL_OR_EMPTY_ERROR
            );
        }

        for (MovieRental movieRental : customer.rentals()) {
            if (movieRental.movieId() == null
                    || movieRental.movieId().isEmpty()
            ) {
                throw new IllegalArgumentException(
                        Constants.CUSTOMER_RENTAL_MOVIE_ID_CANNOT_BE_NULL_OR_EMPTY_ERROR
                );
            }

            if (!movies.containsKey(movieRental.movieId())) {
                throw new IllegalArgumentException(
                        Constants.CUSTOMER_RENTAL_MOVIE_NOT_FOUND_ERROR
                );
            }

            if (movieRental.days() <= BigInteger.ZERO.intValue()) {
                throw new IllegalArgumentException(
                        Constants.CUSTOMER_RENTAL_DAYS_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_ZERO_ERROR
                );
            }
        }
    }
}
