package refactoring.java.service;

import refactoring.java.model.MovieCategory;

/**
 * Provides method to compute the loyalty points for a moview rental.
 */
public interface LoyaltyPointsCalculator {
    public int computePoints(MovieCategory category, int days);
}
