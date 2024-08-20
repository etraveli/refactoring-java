package main.java.rental;

import main.java.movie.Movie;
import main.java.movie.MovieRental;

public class RentalCalculator {

    public double calculateAmount(Movie movie, MovieRental rental) {
        IRentalCalculatorStrategy strategy = getStrategy(movie);
        return strategy.calculateAmount(rental);
    }

    private IRentalCalculatorStrategy getStrategy(Movie movie) {
        switch (movie.getCode()) {
            case REGULAR:
                return new RegularCalculatorStrategy();
            case NEW:
                return new NewReleaseCalculatorStrategy();
            case CHILDRENS:
                return new ChildrenCalculatorStrategy();
            default:
                throw new IllegalArgumentException("Unknown movie code: " + movie.getCode());
        }
    }
}