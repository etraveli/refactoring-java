package com.mithwick93.refactoring.java.service.rentalstrategy;

import java.math.BigDecimal;

/**
 * RentalStrategy interface implements strategy pattern to calculate rental
 * amount and frequent renter points.
 */
public interface RentalStrategy {
    int DEFAULT_FREQUENT_POINTS = 1;

    /**
     * Calculate the rental amount for the movie.
     *
     * @param daysRented days for which movie is rented
     * @return rental amount for the movie
     */
    BigDecimal getRentalAmount(int daysRented);

    /**
     * Calculate the frequent points for the movie.
     *
     * @param daysRented days for which movie is rented
     * @return frequent points for the movie
     */
    default int getFrequentPoints(int daysRented) {
        return DEFAULT_FREQUENT_POINTS;
    }
}
