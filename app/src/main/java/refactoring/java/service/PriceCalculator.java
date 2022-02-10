package refactoring.java.service;

import refactoring.java.model.MovieCategory;

public interface PriceCalculator {
    double computePrice(MovieCategory category, int days);
}
