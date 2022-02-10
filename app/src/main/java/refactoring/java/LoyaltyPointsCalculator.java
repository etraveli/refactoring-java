package refactoring.java;

import refactoring.java.model.MovieCategory;

public class LoyaltyPointsCalculator {
    public int computePoints(MovieCategory category, int days) {
        int points = 1;

        if (category == MovieCategory.NEW && days > 2) {
            points++;
        }

        return points;
    }
}
