package com.mithwick93.refactoring.java.service.impl;

import com.mithwick93.refactoring.java.Constants;
import com.mithwick93.refactoring.java.entity.Customer;
import com.mithwick93.refactoring.java.entity.Movie;
import com.mithwick93.refactoring.java.entity.MovieRental;
import com.mithwick93.refactoring.java.repositroy.MovieRepository;
import com.mithwick93.refactoring.java.service.RentalInformationService;
import com.mithwick93.refactoring.java.service.helper.StatementGeneratorHelper;

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
        Movie.MovieCode movieCode = movie.code();
        int days = movieRental.days();

        double amount = getRentAmount(movieCode, days);
        int frequentPoints = getFrequentPoints(movieCode, days);

        statementGeneratorHelper.addMovieStatement(
                movieTitle,
                amount,
                frequentPoints
        );
    }

    /**
     * Get the rent amount for the movie based on the movie code and no of
     * rented days.
     *
     * @param movieCode movie code
     * @param days      days for which movie is rented
     * @return rent amount
     */
    private double getRentAmount(
            final Movie.MovieCode movieCode,
            final int days
    ) {
        // initialize with base amount
        double amount = movieCode.getBaseRate();

        // add amount for the days after max base rate days
        int daysAfterMaxBaseRateDays = days - movieCode.getMaxBaseRateDays();
        if (daysAfterMaxBaseRateDays > BigInteger.ZERO.intValue()) {
            amount += daysAfterMaxBaseRateDays * movieCode.getDailyRate();
        }

        return amount;
    }

    /**
     * Get the frequent points for the movie based on the movie code and no of
     * rented days.
     *
     * @param movieCode movie code
     * @param days      days for which movie is rented
     * @return frequent points
     */
    private int getFrequentPoints(
            final Movie.MovieCode movieCode,
            final int days
    ) {
        // initialize with regular frequent points
        int frequentPoints = movieCode.getFrequentPoints();

        // add additional frequent points if the movie is eligible for more
        // frequent points and days are more than the threshold
        if (movieCode.isEligibleForMoreFrequentPoints()
                && days > movieCode.getDaysThresholdForMoreFrequentPoints()
        ) {
            frequentPoints += movieCode.getAdditionalFrequentPoints();
        }

        return frequentPoints;
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

        for (MovieRental r : customer.rentals()) {
            if (r.movieId() == null || r.movieId().isEmpty()) {
                throw new IllegalArgumentException(
                        Constants.CUSTOMER_RENTAL_MOVIE_ID_CANNOT_BE_NULL_OR_EMPTY_ERROR
                );
            }

            if (!movies.containsKey(r.movieId())) {
                throw new IllegalArgumentException(
                        Constants.CUSTOMER_RENTAL_MOVIE_NOT_FOUND_ERROR
                );
            }

            if (r.days() <= 0) {
                throw new IllegalArgumentException(
                        Constants.CUSTOMER_RENTAL_DAYS_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_ZERO_ERROR
                );
            }
        }
    }
}
