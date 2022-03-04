package services;

import enums.MovieCode;
import models.MovieRental;

/**
 * service layer for MovieRental model
 */
public class MovieRentalService {

    /**
     * calculates the amount per movie rental depending on the configuration that the movie code have
     * @param movieRental
     * @return calculated amount per movie rental
     */
    public double getAmountPerMovie(MovieRental movieRental){
        // determine amount for each movie
        MovieCode movieCode = movieRental.getMovie().getCode();
        double amount = 0;

        if(movieCode.getDaysToCompare() == null || movieRental.getDays() > movieCode.getDaysToCompare()){
            amount = movieRental.getDays() + movieCode.getValueToBeAdded();
            amount = amount * movieCode.getValueToBeMultiplied();
        }
        amount += movieCode.getStartingAmount();


        return amount;
    }

    /**
     * method which calculates if frequentEnterPoints should be increased by one extra point
     * @param movieRental
     * @return a boolean if condition is fulfilled
     */
    public boolean increaseFrequentEnterPoints(MovieRental movieRental){
        // add bonus for a two day new release rental
        Integer daysToCompareForFrequentEnterPoints = movieRental.getMovie().getCode().getDaysToCompareForFrequentEnterPoints();
        return daysToCompareForFrequentEnterPoints != null && movieRental.getDays() > daysToCompareForFrequentEnterPoints;
    }
}
