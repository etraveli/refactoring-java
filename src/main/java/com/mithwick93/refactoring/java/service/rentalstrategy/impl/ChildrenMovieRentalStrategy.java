package com.mithwick93.refactoring.java.service.rentalstrategy.impl;

import com.mithwick93.refactoring.java.service.rentalstrategy.RentalStrategy;

import java.math.BigDecimal;

/**
 * Rental strategy for children movie.
 */
public class ChildrenMovieRentalStrategy extends RentalStrategy {
    private static final BigDecimal STANDARD_RATE = new BigDecimal("1.5");
    private static final int STANDARD_RENTAL_PERIOD = 3;
    private static final BigDecimal DAILY_RATE = new BigDecimal("1.5");

    /**
     * Calculate the rental amount for the children movie.
     *
     * @param daysRented days for which movie is rented
     * @return rental amount for the movie
     */
    @Override
    public BigDecimal getRentalAmount(final int daysRented) {
        return getRentalAmount(
                daysRented,
                STANDARD_RATE,
                STANDARD_RENTAL_PERIOD,
                DAILY_RATE
        );
    }
}
