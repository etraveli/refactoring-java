package refactoring.java.statement;

import refactoring.java.config.ApplicationConfiguration;
import refactoring.java.model.Customer;
import refactoring.java.model.Movie;
import refactoring.java.model.MovieRental;
import refactoring.java.service.LoyaltyPointsCalculator;
import refactoring.java.service.MovieRepository;
import refactoring.java.service.PriceCalculator;

/**
 * Computes amount and points and prepares the statement to be generated.
 */
public class StatementCalculator {

    MovieRepository movieRepository;
    PriceCalculator priceCalculator;
    LoyaltyPointsCalculator loyaltyPointsCalculator;

    public StatementCalculator(ApplicationConfiguration applicationConfiguration) {
        this.movieRepository = applicationConfiguration.getMovieRepository();
        this.priceCalculator = applicationConfiguration.getPriceCalculator();
        this.loyaltyPointsCalculator = applicationConfiguration.getLoyaltyPointsCalculator();
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
