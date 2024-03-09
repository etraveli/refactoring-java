package com.mithwick93.refactoring.java;

import com.mithwick93.refactoring.java.entity.Customer;
import com.mithwick93.refactoring.java.entity.Movie;
import com.mithwick93.refactoring.java.entity.MovieCode;
import com.mithwick93.refactoring.java.entity.MovieRental;

import java.util.HashMap;
import java.util.Map;

public class RentalInfo {

    public String statement(Customer customer) {
        HashMap<String, Movie> movies = new HashMap();
        movies.put("F001", new Movie("You've Got Mail", MovieCode.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieCode.REGULAR));
        movies.put("F003", new Movie("Cars", MovieCode.CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", MovieCode.NEW_RELEASE));

        validateCustomer(customer, movies);

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        String result = "Rental Record for " + customer.name() + "\n";
        for (MovieRental r : customer.rentals()) {
            double thisAmount = 0;

            // determine amount for each movie
            if (movies.get(r.movieId()).code() == MovieCode.REGULAR) {
                thisAmount = 2;
                if (r.days() > 2) {
                    thisAmount = ((r.days() - 2) * 1.5) + thisAmount;
                }
            }
            if (movies.get(r.movieId()).code() == MovieCode.NEW_RELEASE) {
                thisAmount = r.days() * 3;
            }
            if (movies.get(r.movieId()).code() == MovieCode.CHILDREN) {
                thisAmount = 1.5;
                if (r.days() > 3) {
                    thisAmount = ((r.days() - 3) * 1.5) + thisAmount;
                }
            }

            //add frequent bonus points
            frequentEnterPoints++;
            // add bonus for a two day new release rental
            if (movies.get(r.movieId()).code() == MovieCode.NEW_RELEASE && r.days() > 2) frequentEnterPoints++;

            //print figures for this rental
            result += "\t" + movies.get(r.movieId()).title() + "\t" + thisAmount + "\n";
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
