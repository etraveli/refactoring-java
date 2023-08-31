package com.show.movierental.core.pricing;

public class ChildrensPriceStrategy implements PriceStrategy {
    public double calculatePrice(int days) {
        // Children's price calculation logic
        double price = 1.5;
        if (days > 3) {
            price += (days - 3) * 1.5;
        }
        return price;
    }

    @Override
    public int calculateFrequentRentalPoints(int days) {
        return 1;
    }
}