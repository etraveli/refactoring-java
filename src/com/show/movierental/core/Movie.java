package com.show.movierental.core;

import com.show.movierental.core.pricing.PriceStrategy;

public class Movie {
    private String title;
    private PriceStrategy priceStrategy;

    public Movie(String title, PriceStrategy priceStrategy) {
        this.title = title;
        this.priceStrategy = priceStrategy;
    }

    public String getTitle() {
        return title;
    }

    public double calculatePrice(int days) {
        return priceStrategy.calculatePrice(days);
    }

    public int calculateFrequentRentalPoints(int days) {
        return priceStrategy.calculateFrequentRentalPoints(days);
    }
}