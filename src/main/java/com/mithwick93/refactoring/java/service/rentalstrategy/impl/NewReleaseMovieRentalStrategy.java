package com.mithwick93.refactoring.java.service.rentalstrategy.impl;

import com.mithwick93.refactoring.java.service.rentalstrategy.RentalStrategy;

/**
 * Rental strategy for new movie.
 */
public class NewReleaseMovieRentalStrategy implements RentalStrategy {
    private static final double DAILY_RATE = 3.0;
    private static final int BONUS_FREQUENT_POINTS_THRESHOLD_DAYS = 2;
    private static final int BONUS_FREQUENT_POINTS = 2;

    /**
     * Calculate the rental amount for the new movie.
     *
     * @param daysRented days for which movie is rented
     * @return rental amount for the movie
     */
    @Override
    public double getRentalAmount(final int daysRented) {
        return DAILY_RATE * daysRented;
    }

    /**
     * Calculate the frequent points for the new movie.
     *
     * @param daysRented days for which movie is rented
     * @return frequent points for the movie
     */
    @Override
    public int getFrequentPoints(final int daysRented) {
        return daysRented > BONUS_FREQUENT_POINTS_THRESHOLD_DAYS
                ? BONUS_FREQUENT_POINTS
                : DEFAULT_FREQUENT_POINTS;
    }
}
