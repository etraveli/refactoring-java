package com.etraveli.service;

import com.etraveli.constants.Headers;
import com.etraveli.constants.MovieType;
import com.etraveli.constants.SpecialCharacters;
import com.etraveli.db.repository.MovieRepository;
import com.etraveli.model.Customer;
import com.etraveli.model.Movie;
import com.etraveli.model.MovieRental;
import com.etraveli.model.RentalInformation;
import com.etraveli.pattern.MovieRentals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.etraveli.mappers.MovieMapper.getMovies;

@Service
public class RentalInformationServiceImpl implements RentalInformationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalInformationServiceImpl.class);

    private final MovieRepository movieRepository;

    private final MovieRentals movieRentalFactory;

    public RentalInformationServiceImpl(MovieRepository movieRepository, MovieRentals movieRentalFactory) {
        this.movieRepository = movieRepository;
        this.movieRentalFactory = movieRentalFactory;
    }

    /**
     * @param customer
     * @return A List of Rental Information by calculating the rental prices based on number of days, type of subscription to a movie rental.
     */
    @Override
    public List<RentalInformation> statement(Customer customer) {
        List<RentalInformation> rentalSlip = new ArrayList<>();
        //finding all the movies that are present in the database.
        Map<String, Movie> movies = getMovies(movieRepository.findAll());
        LOGGER.info("Movies Size " + movies.size());
        double totalRentalPrice = 0;
        int frequentEnterPoints = 0;
        for (MovieRental movieRental : customer.getRentals()) {
            //calculate this amount;
            Movie movie = movies.get(movieRental.getMovieId());
            double rentalPrice = calculateRentalPrice(movieRental, movie.getCode());
            frequentEnterPoints++;
            //To-DO: Logic can be implemented in a better way.
            if (MovieType.NEW.toString().equalsIgnoreCase(movieRental.getMovieId()) && movieRental.getDays() > 2) {
                frequentEnterPoints++;
            }
            totalRentalPrice = totalRentalPrice + rentalPrice;
            RentalInformation rentalPriceInfo = new RentalInformation(movie.getTitle(), String.valueOf(rentalPrice), 2);
            rentalSlip.add(rentalPriceInfo);
        }
        createRentalInformation(customer, rentalSlip, totalRentalPrice, frequentEnterPoints);
        rentalSlip.sort(Comparator.comparing(RentalInformation::getPosition));
        return rentalSlip;
    }

    private static void createRentalInformation(Customer customer, List<RentalInformation> rentalSlip,
                                                double totalRentalPrice, int frequentEnterPoints) {
        RentalInformation customerInfo = new RentalInformation(Headers.RECORD_FOR.label, customer.getName(), 1);
        RentalInformation amountOwedInfo = new RentalInformation(Headers.AMOUNT_OWED.label, String.valueOf(totalRentalPrice), 3);
        RentalInformation youEarnedInfo = new RentalInformation(Headers.YOU_EARNED.label, frequentEnterPoints +
                SpecialCharacters.EMPTY_SPACE.character + Headers.FREQUENT_POINTS.label, 4);
        rentalSlip.add(customerInfo);
        rentalSlip.add(amountOwedInfo);
        rentalSlip.add(youEarnedInfo);
    }

    /**
     * @param movieRental
     * @param movieCode
     * @return the rental price for a given number of days, rental price and movie subscription.
     */
    private double calculateRentalPrice(MovieRental movieRental, String movieCode) {
        return this.movieRentalFactory.getMovieRentalType(movieCode)
                .map(movieRentalType -> movieRentalType.getRentalPrice(movieRental.getDays())).orElse(0D);
    }

}
