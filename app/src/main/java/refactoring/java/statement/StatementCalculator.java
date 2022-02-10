package refactoring.java.statement;

import refactoring.java.LoyaltyPointsCalculator;
import refactoring.java.MovieRepository;
import refactoring.java.PriceCalculator;
import refactoring.java.model.Customer;
import refactoring.java.model.Movie;
import refactoring.java.model.MovieRental;

public class StatementCalculator {

    MovieRepository movieRepository;
    PriceCalculator priceCalculator;
    LoyaltyPointsCalculator loyaltyPointsCalculator;

    public StatementCalculator(MovieRepository movieRepository,
                               PriceCalculator priceCalculator,
                               LoyaltyPointsCalculator loyaltyPointsCalculator) {
        this.movieRepository = movieRepository;
        this.priceCalculator = priceCalculator;
        this.loyaltyPointsCalculator = loyaltyPointsCalculator;
    }

    public StatementData computeStatement(Customer customer) {
        if (customer == null) {
            return null;
        }

        StatementData statementData = new StatementData(customer.getName());

        for (MovieRental movieRental : customer.getRentals()) {
            Movie movie = movieRepository.findById(movieRental.getMovieId());
            if (movie == null) {
                continue;
            }

            double amount = priceCalculator.computePrice(movie.getCategory(), movieRental.getDays());
            int loyaltyPoints = loyaltyPointsCalculator.computePoints(movie.getCategory(), movieRental.getDays());

            StatementLineItem statementLineItem = new StatementLineItem(movie.getTitle(), amount, loyaltyPoints);
            statementData.addLineItem(statementLineItem);
        }

        return statementData;
    }
}
