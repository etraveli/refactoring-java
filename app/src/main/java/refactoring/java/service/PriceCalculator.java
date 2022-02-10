package refactoring.java.service;

import refactoring.java.model.MovieCategory;

/**
 * Provides a method to compute the price for a movie rental.
 */
public interface PriceCalculator {
    double computePrice(MovieCategory category, int days);
}
