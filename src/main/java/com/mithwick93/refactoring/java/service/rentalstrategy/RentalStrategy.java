package com.mithwick93.refactoring.java.service.rentalstrategy;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * RentalStrategy interface implements strategy pattern to calculate rental
 * amount and frequent renter points.
 */
public abstract class RentalStrategy {
    protected static final int DEFAULT_FREQUENT_POINTS = 1;

    /**
     * Calculate the frequent points for the movie.
     *
     * @param daysRented days for which movie is rented
     * @return frequent points for the movie
     */
    public int getFrequentPoints(int daysRented) {
        return DEFAULT_FREQUENT_POINTS;
    }

    /**
     * Calculate the rental amount for the movie.
     *
     * @param daysRented days for which movie is rented
     * @return rental amount for the movie
     */
    abstract public BigDecimal getRentalAmount(int daysRented);

    /**
     * Default implementation to calculate rental amount for the movie.
     *
     * @param daysRented           days for which movie is rented
     * @param standardRate         standard rate for the movie
     * @param standardRentalPeriod standard rental period for the movie
     * @param dailyRate            daily rate for the movie
     * @return rental amount for the movie
     */
    protected BigDecimal getRentalAmount(
            final int daysRented,
            final BigDecimal standardRate,
            final int standardRentalPeriod,
            final BigDecimal dailyRate
    ) {
        BigDecimal amount = standardRate;

        int daysAfterMaxBaseRateDays = daysRented - standardRentalPeriod;
        if (daysAfterMaxBaseRateDays > BigInteger.ZERO.intValue()) {
            BigDecimal additionalAmount = dailyRate
                    .multiply(BigDecimal.valueOf(daysAfterMaxBaseRateDays));
            amount = amount.add(additionalAmount);
        }

        return amount;
    }
}
