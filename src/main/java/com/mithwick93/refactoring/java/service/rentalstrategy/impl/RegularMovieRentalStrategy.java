package com.mithwick93.refactoring.java.service.rentalstrategy.impl;

import com.mithwick93.refactoring.java.service.rentalstrategy.RentalStrategy;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Rental strategy for regular movie.
 */
public class RegularMovieRentalStrategy implements RentalStrategy {
    private static final BigDecimal STANDARD_RATE = new BigDecimal("2.0");
    private static final int STANDARD_RENTAL_PERIOD = 2;
    private static final BigDecimal DAILY_RATE = new BigDecimal("1.5");

    /**
     * Calculate the rental amount for the regular movie.
     *
     * @param daysRented days for which movie is rented
     * @return rental amount for the movie
     */
    @Override
    public BigDecimal getRentalAmount(final int daysRented) {
        BigDecimal amount = STANDARD_RATE;

        int daysAfterMaxBaseRateDays = daysRented - STANDARD_RENTAL_PERIOD;
        if (daysAfterMaxBaseRateDays > BigInteger.ZERO.intValue()) {
            BigDecimal additionalAmount = DAILY_RATE
                    .multiply(BigDecimal.valueOf(daysAfterMaxBaseRateDays));
            amount = amount.add(additionalAmount);
        }

        return amount;
    }
}
