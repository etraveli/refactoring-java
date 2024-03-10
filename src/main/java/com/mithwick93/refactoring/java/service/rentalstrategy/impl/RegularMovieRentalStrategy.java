package com.mithwick93.refactoring.java.service.rentalstrategy.impl;

import com.mithwick93.refactoring.java.service.rentalstrategy.RentalStrategy;

import java.math.BigInteger;

/**
 * Rental strategy for regular movie.
 */
public class RegularMovieRentalStrategy implements RentalStrategy {
    private static final double STANDARD_RATE = 2.0;
    private static final int STANDARD_RENTAL_PERIOD = 2;
    private static final double DAILY_RATE = 1.5;

    /**
     * Calculate the rental amount for the regular movie.
     *
     * @param daysRented days for which movie is rented
     * @return rental amount for the movie
     */
    @Override
    public double getRentalAmount(final int daysRented) {
        double amount = STANDARD_RATE;

        int daysAfterMaxBaseRateDays = daysRented - STANDARD_RENTAL_PERIOD;
        if (daysAfterMaxBaseRateDays > BigInteger.ZERO.intValue()) {
            amount += daysAfterMaxBaseRateDays * DAILY_RATE;
        }

        return amount;
    }
}
