package main.java.rental;

import main.java.movie.MovieRental;

public interface IRentalCalculatorStrategy {
    double calculateAmount(MovieRental movieRental);
}
