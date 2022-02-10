package refactoring.java.service;

import refactoring.java.model.MovieCategory;

public interface LoyaltyPointsCalculator {
    public int computePoints(MovieCategory category, int days);
}
