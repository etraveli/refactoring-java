package refactoring.java;

import refactoring.java.model.MovieCategory;

public class PriceCalculator {
    PriceCalculationResult computePriceAndPoints(MovieCategory category, int days) {
        double price = computePrice(category, days);
        int points = computePoints(category, days);

        return new PriceCalculationResult(price, points);
    }

    private int computePoints(MovieCategory category, int days) {
        int points = 1;

        if (category == MovieCategory.NEW && days > 2) {
            points++;
        }

        return points;
    }

    private double computePrice(MovieCategory category, int days) {
        int includedDays = 0;
        double initialAmount = 0.0;
        double dailyAmount = 0.0;

        switch (category) {
            case REGULAR -> {
                includedDays = 2;
                initialAmount = 2.0;
                dailyAmount = 1.5;
            }
            case CHILDRENS -> {
                includedDays = 3;
                initialAmount = 1.5;
                dailyAmount = 1.5;
            }
            case NEW -> {
                includedDays = 0;
                initialAmount = 0.0;
                dailyAmount = 3.0;
            }
            default -> throw new IllegalStateException("Unexpected value: " + category);
        }

        int daysToBecharged = (days - includedDays > 0 ? days - includedDays : 0);
        double price = initialAmount + daysToBecharged * dailyAmount;
        return price;
    }
}
