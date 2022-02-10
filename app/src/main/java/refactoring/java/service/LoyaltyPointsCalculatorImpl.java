package refactoring.java.service;

import refactoring.java.model.MovieCategory;

/**
 * Implements the loyalty point calculation.
 */
public class LoyaltyPointsCalculatorImpl implements LoyaltyPointsCalculator {
    @Override
    public int computePoints(MovieCategory category, int days) {
        int points = 1;

        if (category == MovieCategory.NEW && days > 2) {
            points++;
        }

        return points;
    }
}
