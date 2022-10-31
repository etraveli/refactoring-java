package com.etraveli.movie.rental.services;

import com.etraveli.movie.rental.exceptions.MovieCodeDoesNotExistException;
import com.etraveli.movie.rental.models.Customer;
import com.etraveli.movie.rental.models.Movie;
import com.etraveli.movie.rental.models.MovieRental;

/**
 * A service that provide rental information of customer.
 */
public class RentalInfo {

    private static final String OWED_AMOUNT_LOG = "Amount owed is %s\n";
    private static final String FREQUENT_POINTS_LOG = "You earned %s frequent points\n";
    private static final String CUSTOMER_NAME_LOG = "Rental Record for %s\n";

    /**
     * A method to get rentol information of given customer.
     *
     * @param customer to get rental information
     * @return rental information result as String
     */
    public String getRentalRecordOfCustomer(final Customer customer) {
        double totalAmount = 0;
        int frequentEnterPoints = 0;
        final StringBuilder info = new StringBuilder(String.format(CUSTOMER_NAME_LOG, customer.getName()));

        for (MovieRental movieRental : customer.getRentals()) {

            final Movie movie = MovieService.getMovieByMovieId(movieRental.getMovieId());
            double amount = getAmountOfEachRentedMovie(movieRental.getDays(), movie.getCode());
            info.append("\t").append(movie.getTitle()).append("\t").append(amount).append("\n");
            totalAmount += amount;

            frequentEnterPoints = getFrequencyPointsForEachMovieRental(movieRental, frequentEnterPoints);
        }

        // add footer lines
        info.append(String.format(OWED_AMOUNT_LOG, totalAmount));
        info.append(String.format(FREQUENT_POINTS_LOG, frequentEnterPoints));
        return info.toString();
    }

    /**
     * A method to get  amount of each rented movie.
     *
     * @param rentalDays to be rented.
     * @param movieCode  of the rented movie.
     * @return amount of each rented movie.
     */
    private double getAmountOfEachRentedMovie(final int rentalDays, final Movie.MovieCode movieCode) {
        double amount = 0;
        switch (movieCode) {
            case REGULAR:
                amount = getRegularAmount(rentalDays);
                break;
            case NEW:
                amount = getNewAmount(rentalDays);
                break;
            case CHILDREN:
                amount = getChildrenAmount(rentalDays);
                break;
            default:
                throw new MovieCodeDoesNotExistException(movieCode);
        }

        return amount;
    }

    /**
     * Calculates the amount of the rented movie with regular code.
     *
     * @param rentalDays to be rented.
     * @return amount that is calculated.
     */
    private double getRegularAmount(final int rentalDays) {
        return calculateAmountForRegularAndChildren(rentalDays, 2, 2);
    }

    /**
     * Calculates the amount of the rented movie with new code.
     *
     * @param rentalDays to be rented.
     * @return amount that is calculated.
     */
    private double getNewAmount(final int rentalDays) {
        return rentalDays * (double) 3;
    }

    /**
     * Calculates the amount of the rented movie with children code.
     *
     * @param rentalDays to be rented.
     * @return amount that is calculated.
     */
    private double getChildrenAmount(final int rentalDays) {
        return calculateAmountForRegularAndChildren(rentalDays, 3, 1.5);
    }

    /**
     * Calculate amount for rented movie with regular or children code.
     *
     * @param rentalDays    to be rented.
     * @param dayThreshold  that is the day number that we start to multiply by the daily amount with rest of the rentalDays.
     * @param minimumAmount that added at the beginning of the renting period.
     * @return amount that is calculated.
     */
    private double calculateAmountForRegularAndChildren(int rentalDays, int dayThreshold, double minimumAmount) {
        final double dailyAmountAfterDayThreshold = 1.5;
        return rentalDays > dayThreshold ? (((rentalDays - dayThreshold) * dailyAmountAfterDayThreshold) + minimumAmount) : minimumAmount;
    }

    /**
     * Calculates latest frequentEnterPoints.
     *
     * @param movieRental         that is given to calculate the frequency points.
     * @param frequentEnterPoints to be increased.
     * @return latest frequency enter points.
     */
    private int getFrequencyPointsForEachMovieRental(final MovieRental movieRental, int frequentEnterPoints) {
        final Movie movie = MovieService.getMovieByMovieId(movieRental.getMovieId());
        if (movie.getCode() == Movie.MovieCode.NEW && movieRental.getDays() > 2) {
            ++frequentEnterPoints;
        }
        return ++frequentEnterPoints;
    }

}
