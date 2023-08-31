package com.show.movierental.core.pricing;


public class NewReleasePriceStrategy implements PriceStrategy {
    public double calculatePrice(int days) {
        // New release price calculation logic
        return days * 3;
    }

    @Override
    public int calculateFrequentRentalPoints(int days) {
        int points = 1;
        if ( days > 2) {
            points++;
        }
        return points;
    }
}