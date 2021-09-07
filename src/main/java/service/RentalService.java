package service;

import entity.Customer;
import entity.Movie;
import entity.MovieRental;
import entity.RentalResult;
import helper.MovieHelper;
import org.apache.log4j.Logger;
import run.Main;

import java.util.HashMap;

/**
 * RentalService class which is responsible with Movie Rental operations
 */
public class RentalService {

    private static Logger logger = Logger.getLogger(RentalService.class);


    /**
     * Creates Rental Statement
     * @param customer
     * @return
     */
    public RentalResult createRentalStatement(Customer customer) {
        RentalResult rentalResult = generateRentalResult(customer);
        return rentalResult;
    }


    /**
     * Generetas Rental Result from the provided customers
     * @param customer
     * @return
     */
    public RentalResult generateRentalResult(Customer customer) {

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        HashMap<String, Movie> movieMap = MovieHelper.generateMoviesMap();
        logger.debug("movieMap is generated Successully. Total size is " + movieMap.values().size());

        for (MovieRental r : customer.getRentals()) {
            double thisAmount;

            switch (movieMap.get(r.getMovieId()).getMovieType()) {
                case REGULAR:
                    thisAmount = 2;
                    if (r.getDays() > 2) {
                        thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
                    }
                    break;

                case NEW:
                    thisAmount = r.getDays() * 3;
                    // add bonus for a two day new release rental
                    if (r.getDays() > 2) {
                        frequentEnterPoints++;
                    }
                    break;

                default:
                    thisAmount = 1.5;
                    if (r.getDays() > 3) {
                        thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
                    }
                    break;
            }

            //add frequent bonus points
            frequentEnterPoints++;
            movieMap.get(r.getMovieId()).setAmount(thisAmount);
            totalAmount = totalAmount + thisAmount;
            logger.debug("total Amount of  movie " + movieMap.get(r.getMovieId()) + " is " + totalAmount  + " frequency is " + frequentEnterPoints);
        }

        RentalResult rentalResult = new RentalResult(totalAmount, frequentEnterPoints, movieMap);
        return rentalResult;
    }
}
