package com.etraveliGroup.service;

import com.etraveliGroup.entity.Customer;
import com.etraveliGroup.entity.Movie;
import com.etraveliGroup.entity.MovieRental;
import com.etraveliGroup.exception.InvalidCustomerException;
import com.etraveliGroup.exception.InvalidDaysException;
import com.etraveliGroup.exception.InvalidMoveIdException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.etraveliGroup.constants.RentalInfoConstants.*;

@Service
public class RentalInfoService {

    /**
     * Get Rental Info for the customer.
     *
     * @param customer
     * @return String
     */

    public String getRentalInfo(Customer customer) {
        //Checking CustomerDetails and rental details whether valid or not.
        if (customer.getRentals() == null || customer.getRentals().isEmpty() || customer.getName() == null || customer.getName() ==""){
            throw new InvalidCustomerException(INVALID_CUSTOMER_DETAILS);
        }

        HashMap<String, Movie> movies = new HashMap();

        movies.put("F001", new Movie("You've Got Mail",REGULAR));
        movies.put("F002", new Movie("Matrix", REGULAR));
        movies.put("F003", new Movie("Cars", CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", NEW));

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        String result = RENTAL_RECORD + customer.getName() + "\n";
        for (MovieRental r : customer.getRentals()) {
            //Checking MovieId is valid or not.
            if(movies.get(r.getMovieId()) == null){
                throw new InvalidMoveIdException(INVALID_MOVIEID);
            }
            Movie movie = movies.get(r.getMovieId());
            int days = r.getDays();
            if (days == 0){
                throw new InvalidDaysException(INVALID_RENT_DAYS);
            }
            double thisAmount = calculateRent( movie, days);

            //add frequent bonus points
            frequentEnterPoints++;
            // add bonus for a two day new release rental
            if (movies.get(r.getMovieId()).getCode() == NEW && r.getDays() > 2) frequentEnterPoints++;

            //print figures for this rental
            result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        result += AMOUNT_OWED + totalAmount + "\n" + EARNED + frequentEnterPoints + FREQUENCY_POINTS;
        return result;
    }

    /**
     * Calculate Rent
     * @param movie
     * @param days
     * @return double
     */
    private double calculateRent( Movie movie, int days ) {
        double rent = 0.0d;

        switch( movie.getCode() ) {
            case REGULAR:
                rent = 2;
                if (days > 2) {
                    rent = ((days - 2) * 1.5) + rent;
                }
                break;
            case NEW:
                rent = days * 3;
                break;
            case CHILDRENS:
                rent = 1.5;
                if (days > 3) {
                    rent = ((days - 3) * 1.5) + rent;
                }
                break;
            default:
                throw new InvalidMoveIdException(INVALID_MOVIEID);
        }

        return rent;
    }
}
