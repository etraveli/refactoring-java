package com.mithwick93.refactoring.java.service.rentalstrategy.impl;

import com.mithwick93.refactoring.java.service.rentalstrategy.RentalStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewReleaseMovieRentalStrategyTest {
    private RentalStrategy rentalStrategy;

    @BeforeEach
    void setUp() {
        rentalStrategy = new NewReleaseMovieRentalStrategy();
    }

    @Test
    void givenDaysRentedLessThanStandardRentalPeriod_whenGetRentalAmount_thenReturnRentalAmount() {
        int daysRented = 1;
        double expectedRentalAmount = 3.0;

        double actualRentalAmount = rentalStrategy.getRentalAmount(daysRented);

        assertEquals(expectedRentalAmount, actualRentalAmount, "Rental amount should be 3.0");
    }

    @Test
    void givenDaysRentedLessThanBonusThreshold_whenGetFrequentPoints_thenReturnsFrequentPoints() {
        int daysRented = 1;
        int expectedFrequentPoints = 1;

        int actualFrequentPoints = rentalStrategy.getFrequentPoints(daysRented);

        assertEquals(
                expectedFrequentPoints, actualFrequentPoints, "Frequent points should be 1"
        );
    }

    @Test
    void givenDaysRentedGreatThanBonusThreshold_whenGetFrequentPoints_thenReturnsFrequentPoints() {
        int daysRented = 6;
        int expectedFrequentPoints = 2;

        int actualFrequentPoints = rentalStrategy.getFrequentPoints(daysRented);

        assertEquals(
                expectedFrequentPoints, actualFrequentPoints, "Frequent points should be 2"
        );
    }
}
