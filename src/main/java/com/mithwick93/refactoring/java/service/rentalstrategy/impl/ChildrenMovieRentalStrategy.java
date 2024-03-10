package com.mithwick93.refactoring.java.service.rentalstrategy.impl;

import com.mithwick93.refactoring.java.service.rentalstrategy.RentalStrategy;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Rental strategy for children movie.
 */
public class ChildrenMovieRentalStrategy implements RentalStrategy {
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
