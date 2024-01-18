package com.etraveli.service;

import com.etraveli.model.Customer;
import com.etraveli.model.Movie;
import com.etraveli.model.MovieRental;
import com.etraveli.repository.MovieRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.platform.commons.util.StringUtils;

import java.util.Locale;

import static com.etraveli.util.Constants.*;

public class RentalInfoService implements RentalService {
    private final MovieRepository movieRepository;
    private static final Logger logger = LogManager.getLogger(RentalInfoService.class);

    public RentalInfoService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Returns Rental Information as text. The Rental Information contains Customer's name, each rental entry including movie title and calculated rental amount,
     * total rental amount and frequent points.
     * If the rental has negative value of days or movie title is blank or movie code is blank, the rental will be skipped (excluded from the rental report),
     * and warning will be logged.
     *
     * @param customer the person for which the Rental Info would be returned
     * @return the textual representation of Rental Information
     */
    public String getRentalInfoForCustomer(Customer customer) {
        if (customer == null) {
            logger.error(RENTAL_RECORD_LOGGING_INVALID_CUSTOMER);
            return RENTAL_RECORD_INVALID_CUSTOMER;
        }
        StringBuilder result = new StringBuilder(String.format(Locale.US, RENTAL_RECORD_CUSTOMER, customer.name()));
        double totalRentalAmount = 0;
        int frequentEnterPoints = 0;

        for (MovieRental rental : customer.rentals()) {
            Movie movie = movieRepository.getMovieById(rental.movieId());
            if (!isRentalValid(rental, movie, customer)) {
                continue;
            }

            double rentalEntryAmount = movie.code().amount(rental.days());
            result.append(String.format(Locale.US, RENTAL_RECORD_ENTRY, movie.title(), rentalEntryAmount));
            totalRentalAmount += rentalEntryAmount;
            frequentEnterPoints += movie.code().frequentEnterPoints(rental.days());
        }

        result.append(String.format(Locale.US, RENTAL_RECORD_OWNED, totalRentalAmount));
        result.append(String.format(Locale.US, RENTAL_RECORD_FREQUENT_POINTS, frequentEnterPoints));

        return result.toString();
    }

    private boolean isRentalValid(MovieRental rental, Movie movie, Customer customer) {
        if (rental.days() < 0 || StringUtils.isBlank(movie.title()) || movie.code() == null) {
            if (logger.isWarnEnabled()) {
                logger.warn(RENTAL_RECORD_LOGGING_SKIP, customer.name(), movie.title(), movie.code(), rental.days());
            }
            return false;
        } else {
            return true;
        }
    }
}
