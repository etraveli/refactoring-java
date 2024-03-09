package com.mithwick93.refactoring.java;

import com.mithwick93.refactoring.java.entity.Customer;
import com.mithwick93.refactoring.java.entity.Movie;
import com.mithwick93.refactoring.java.entity.MovieCode;
import com.mithwick93.refactoring.java.entity.MovieRental;
import com.mithwick93.refactoring.java.repositroy.MovieRepository;

import java.util.Map;

public class RentalInfo {
    private final MovieRepository movieRepository;

    public RentalInfo(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String statement(Customer customer) {
        validateCustomer(customer, movieRepository.getMovies());

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        String result = "Rental Record for " + customer.name() + "\n";
        for (MovieRental r : customer.rentals()) {
            Movie movie = movieRepository.getMovie(r.movieId());
            double thisAmount = 0;

            // determine amount for each movie
            if (movie.code() == MovieCode.REGULAR) {
                thisAmount = 2;
                if (r.days() > 2) {
                    thisAmount = ((r.days() - 2) * 1.5) + thisAmount;
                }
            }
            if (movie.code() == MovieCode.NEW_RELEASE) {
                thisAmount = r.days() * 3;
            }
            if (movie.code() == MovieCode.CHILDREN) {
                thisAmount = 1.5;
                if (r.days() > 3) {
                    thisAmount = ((r.days() - 3) * 1.5) + thisAmount;
                }
            }

            //add frequent bonus points
            frequentEnterPoints++;
            // add bonus for a two day new release rental
            if (movie.code() == MovieCode.NEW_RELEASE && r.days() > 2)
                frequentEnterPoints++;

            //print figures for this rental
            result += "\t" + movie.title() + "\t" + thisAmount + "\n";
            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentEnterPoints + " frequent points\n";

        return result;
    }

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
