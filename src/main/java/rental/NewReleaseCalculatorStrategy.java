package main.java.rental;

import main.java.movie.MovieRental;

public class NewReleaseCalculatorStrategy implements IRentalCalculatorStrategy {

    private static final double NEW_RELEASE_RATE = 3.0;

    @Override
    public double calculateAmount(MovieRental rental) {
        // Calculate the rental amount for a new release movie
        return rental.getDays() * NEW_RELEASE_RATE;
    }
}