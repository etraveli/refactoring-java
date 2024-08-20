package main.java.rental;

import java.util.HashMap;

import main.java.customer.Customer;
import main.java.movie.InMemoryMovieRepository;
import main.java.movie.Movie;
import main.java.movie.MovieGenre;
import main.java.movie.MovieRental;

public class RentalStatementGenerator {
    private InMemoryMovieRepository movieRepository;
    private RentalCalculator rentalCalculator;

    public RentalStatementGenerator(InMemoryMovieRepository movieRepository, RentalCalculator rentalCalculator) {
        this.movieRepository = movieRepository;
        this.rentalCalculator = rentalCalculator;
    }

    public String generateStatement(Customer customer) {
        HashMap<String, Movie> movies = movieRepository.getMovies();

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        StringBuilder result = new StringBuilder(generateHeader(customer));

        for (MovieRental rental : customer.getRentals()) {
            Movie movie = movies.get(rental.getMovieId());

            double thisAmount = rentalCalculator.calculateAmount(movie, rental);

            frequentEnterPoints += calculateFrequentEnterPoints(movie, rental);

            result.append(generateRentalLine(movie, thisAmount));
            totalAmount += thisAmount;
        }

        result.append(generateFooter(totalAmount, frequentEnterPoints));
        return result.toString();
    }

    private int calculateFrequentEnterPoints(Movie movie, MovieRental rental) {
        int points = 1;
        if (isBonusApplicable(movie, rental)) {
            points++;
        }
        return points;
    }

    private boolean isBonusApplicable(Movie movie, MovieRental rental) {
        return movie.getCode().equals(MovieGenre.NEW) && rental.getDays() > 2;
    }

    private String generateHeader(Customer customer) {
        return "Rental Record for " + customer.getName() + "\n";
    }

    private String generateRentalLine(Movie movie, double amount) {
        return "\t" + movie.getTitle() + "\t" + amount + "\n";
    }

    private String generateFooter(double totalAmount, int frequentEnterPoints) {
        return "Amount owed is " + totalAmount + "\n" +
                "You earned " + frequentEnterPoints + " frequent points\n";
    }

}