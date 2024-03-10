package com.mithwick93.refactoring.java.service.rentalstrategy.impl;

import com.mithwick93.refactoring.java.service.rentalstrategy.RentalStrategy;

import java.math.BigInteger;

/**
 * Rental strategy for children movie.
 */
public class ChildrenMovieRentalStrategy implements RentalStrategy {
    private static final double STANDARD_RATE = 1.5;
    private static final int STANDARD_RENTAL_PERIOD = 3;
    private static final double DAILY_RATE = 1.5;

    /**
     * Calculate the rental amount for the children movie.
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
