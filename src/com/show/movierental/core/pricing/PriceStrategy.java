package com.show.movierental.core.pricing;

public interface PriceStrategy {
    double calculatePrice(int days);
    int calculateFrequentRentalPoints(int days);
}