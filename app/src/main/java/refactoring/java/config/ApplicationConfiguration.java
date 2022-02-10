package refactoring.java.config;

import refactoring.java.service.LoyaltyPointsCalculator;
import refactoring.java.service.MovieRepository;
import refactoring.java.service.PriceCalculator;

/**
 * Provides services to the application.
 */
public interface ApplicationConfiguration {
    public LoyaltyPointsCalculator getLoyaltyPointsCalculator();

    public MovieRepository getMovieRepository();

    public PriceCalculator getPriceCalculator();
}
