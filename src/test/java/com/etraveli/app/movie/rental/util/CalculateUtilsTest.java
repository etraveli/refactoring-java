package com.etraveli.app.movie.rental.util;

import com.etraveli.app.movie.rental.model.MovieCategory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for rental price and frequent points calculations
 */
class CalculateUtilsTest {

    @Test
    @DisplayName("Calculate Rental Price - Regular")
    void calculateRentalPriceForRegular() {
        double price = CalculateUtils.calculateRentalPrice(MovieCategory.REGULAR, 2);
        assertEquals(2, price);
    }

    @Test
    @DisplayName("Calculate Rental Price - Regular with Extra Days")
    void calculateRentalPriceForRegularExtraDays() {
        double price = CalculateUtils.calculateRentalPrice(MovieCategory.REGULAR, 3);
        assertEquals(3.5, price);
    }

    @Test
    @DisplayName("Calculate Rental Price - Children")
    void calculateRentalPriceForChildrens() {
        double price = CalculateUtils.calculateRentalPrice(MovieCategory.CHILDRENS, 3);
        assertEquals(1.5, price);
    }

    @Test
    @DisplayName("Calculate Rental Price - Children with Extra Days")
    void calculateRentalPriceForChildrensExtraDays() {
        double price = CalculateUtils.calculateRentalPrice(MovieCategory.CHILDRENS, 4);
        assertEquals(3, price);
    }

    @Test
    @DisplayName("Calculate Rental Price - New")
    void calculateRentalPriceForNew() {
        double price = CalculateUtils.calculateRentalPrice(MovieCategory.NEW, 2);
        assertEquals(6, price);
    }

    @Test
    @DisplayName("Calculate Frequent Points - Regular")
    void calculateFrequentPointsRegular() {
        int points = CalculateUtils.calculateFrequentPoints(MovieCategory.REGULAR, 2);
        assertEquals(1, points);
    }

    @Test
    @DisplayName("Calculate Frequent Points - Children")
    void calculateFrequentPointsChildren() {
        int points = CalculateUtils.calculateFrequentPoints(MovieCategory.CHILDRENS, 2);
        assertEquals(1, points);
    }

    @Test
    @DisplayName("Calculate Frequent Points - New")
    void calculateFrequentPointsNew() {
        int points = CalculateUtils.calculateFrequentPoints(MovieCategory.NEW, 2);
        assertEquals(1, points);
    }

    @Test
    @DisplayName("Calculate Frequent Points - New with Extra Days")
    void calculateFrequentPointsNewExtraDays() {
        int points = CalculateUtils.calculateFrequentPoints(MovieCategory.NEW, 3);
        assertEquals(2, points);
    }
}