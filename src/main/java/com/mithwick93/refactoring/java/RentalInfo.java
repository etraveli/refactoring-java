package com.mithwick93.refactoring.java;

import com.mithwick93.refactoring.java.entity.Customer;
import com.mithwick93.refactoring.java.entity.Movie;
import com.mithwick93.refactoring.java.entity.MovieCode;
import com.mithwick93.refactoring.java.entity.MovieRental;
import com.mithwick93.refactoring.java.repositroy.MovieRepository;

import java.math.BigInteger;
import java.util.Map;

public class RentalInfo {
    private final MovieRepository movieRepository;

    public RentalInfo(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Generate the statement for the customer
     *
     * @param customer customer for which statement is to be generated
     * @return statement for the customer
     */
    public String statement(Customer customer) {
        validateCustomer(customer, movieRepository.getMovies());

        double totalAmount = 0;
        int frequentPoints = 0;
        String result = "Rental Record for " + customer.name() + "\n";

        for (MovieRental r : customer.rentals()) {
            Movie movie = movieRepository.getMovie(r.movieId());

            // determine amount for each movie
            double thisAmount = getRentAmount(movie.code(), r.days());
            //add frequent bonus points
            frequentPoints += getFrequentPoints(movie.code(), r.days());

            //print figures for this rental
            result += "\t" + movie.title() + "\t" + thisAmount + "\n";
            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentPoints + " frequent points\n";

        return result;
    }

    /**
     * Get the rent amount for the movie based on the movie code and no of rented days
     *
     * @param movieCode movie code
     * @param days      days for which movie is rented
     * @return rent amount
     */
    private double getRentAmount(MovieCode movieCode, int days) {
        // initialize with base amount
        double amount = movieCode.getBaseRate();

        // add amount for the days after max base rate days
        int daysAfterMaxBaseRate = days - movieCode.getMaxBaseRateDays();
        if (daysAfterMaxBaseRate > BigInteger.ZERO.intValue()) {
            amount += daysAfterMaxBaseRate * movieCode.getDailyRate();
        }

        return amount;
    }

    /**
     * Get the frequent points for the movie based on the movie code and no of rented days
     *
     * @param movieCode movie code
     * @param days      days for which movie is rented
     * @return frequent points
     */
    private int getFrequentPoints(MovieCode movieCode, int days) {
        // initialize with regular frequent points
        int frequentPoints = movieCode.getFrequentPoints();

        // add additional frequent points if the movie is eligible for more frequent points and days are more than the threshold
        if (movieCode.isEligibleForMoreFrequentPoints() && days > movieCode.getDaysThresholdForMoreFrequentPoints()) {
            frequentPoints += movieCode.getAdditionalFrequentPoints();
        }

        return frequentPoints;
    }

    /**
     * Validate the customer and the rentals
     *
     * @param customer customer to validate
     * @param movies   movies to validate the rentals
     */
    private void validateCustomer(Customer customer, Map<String, Movie> movies) {
        if (customer == null) {
            throw new IllegalArgumentException(Constants.CUSTOMER_CANNOT_BE_NULL_ERROR);
        }

        if (customer.name() == null || customer.name().isEmpty()) {
            throw new IllegalArgumentException(Constants.CUSTOMER_NAME_CANNOT_BE_NULL_OR_EMPTY_ERROR);
        }

        if (customer.rentals() == null || customer.rentals().isEmpty()) {
            throw new IllegalArgumentException(Constants.CUSTOMER_RENTALS_CANNOT_BE_NULL_OR_EMPTY_ERROR);
        }

        for (MovieRental r : customer.rentals()) {
            if (r.movieId() == null || r.movieId().isEmpty()) {
                throw new IllegalArgumentException(Constants.CUSTOMER_RENTAL_MOVIE_ID_CANNOT_BE_NULL_OR_EMPTY_ERROR);
            }

            if (!movies.containsKey(r.movieId())) {
                throw new IllegalArgumentException(Constants.CUSTOMER_RENTAL_MOVIE_NOT_FOUND_ERROR);
            }

            if (r.days() <= 0) {
                throw new IllegalArgumentException(Constants.CUSTOMER_RENTAL_DAYS_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_ZERO_ERROR);
            }
        }
    }
}
