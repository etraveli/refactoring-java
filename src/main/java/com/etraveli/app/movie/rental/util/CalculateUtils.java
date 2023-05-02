package com.etraveli.app.movie.rental.util;

import com.etraveli.app.movie.rental.model.MovieCategory;

/**
 * Util class to separate business logic calculations
 */
public class CalculateUtils {

    /**
     * Calculate rental price of a movie
     * @param category MovieCategory object
     * @param noOfDays int value - number of days of the rental
     * @return double - calculated price for the movie rental
     */
    public static double calculateRentalPrice(MovieCategory category, int noOfDays) {
        double price = 0;

        // determine amount for each movie
        switch (category) {
            case REGULAR -> {
                price = 2;
                if (noOfDays > 2) {
                    price = ((noOfDays - 2) * 1.5) + price;
                }
            }
            case CHILDRENS -> {
                price = 1.5;
                if (noOfDays > 3) {
                    price = ((noOfDays - 3) * 1.5) + price;
                }
            }
            case NEW -> price = noOfDays * 3;
        }

        return price;
    }

    /**
     * Calculate frequent points for a movie
     * @param category MovieCategory object
     * @param noOfDays int value - number of days of the rental
     * @return int - calculated frequent points for a movie
     */
    public static int calculateFrequentPoints(MovieCategory category, int noOfDays) {
        int frequentPoints = 1;
        // add bonus for a two days new release rental
        if (category == MovieCategory.NEW && noOfDays > 2)
            frequentPoints++;

        return frequentPoints;
    }
}
