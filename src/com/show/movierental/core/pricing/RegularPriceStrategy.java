package com.show.movierental.core.pricing;


public class RegularPriceStrategy implements PriceStrategy {
    public double calculatePrice(int days) {
        // Regular price calculation logic
        double price = 2;
        if (days > 2) {
            price += (days - 2) * 1.5;
        }
        return price;
    }

    @Override
    public int calculateFrequentRentalPoints(int days) {
        return 1;
    }
}