package com.etraveli.movie.rental;

import com.etraveli.movie.rental.models.Customer;
import com.etraveli.movie.rental.models.MovieRental;
import com.etraveli.movie.rental.services.MovieService;
import com.etraveli.movie.rental.services.RentalInfo;

import java.util.Arrays;


public class Main {
static final String EXPECTED_RESULT = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
    public static void main(String[] args) {
        MovieService.addConstantMovies();
        final String result = new RentalInfo().getRentalRecordOfCustomer(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        if (!result.equals(EXPECTED_RESULT)) {
            throw new AssertionError("Expected: " + System.lineSeparator() + EXPECTED_RESULT + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
        }

        System.out.println("Success");
    }
}
