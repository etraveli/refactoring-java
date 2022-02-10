package refactoring.java.config;

import refactoring.java.service.LoyaltyPointsCalculator;
import refactoring.java.service.MovieRepository;
import refactoring.java.service.PriceCalculator;

public class ApplicationConfigurationImpl implements ApplicationConfiguration {
    LoyaltyPointsCalculator loyaltyPointsCalculator;
    MovieRepository movieRepository;
    PriceCalculator priceCalculator;

    public ApplicationConfigurationImpl(LoyaltyPointsCalculator loyaltyPointsCalculator, MovieRepository movieRepository, PriceCalculator priceCalculator) {
        this.loyaltyPointsCalculator = loyaltyPointsCalculator;
        this.movieRepository = movieRepository;
        this.priceCalculator = priceCalculator;
    }

    @Override
    public LoyaltyPointsCalculator getLoyaltyPointsCalculator() {
        return loyaltyPointsCalculator;
    }

    @Override
    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    @Override
    public PriceCalculator getPriceCalculator() {
        return priceCalculator;
    }
}
