package com.movie.rental.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.movie.rental.constants.MovieRentalConstants;
import com.movie.rental.customexception.InvalidCustomerException;
import com.movie.rental.customexception.InvalidRentDaysException;
import com.movie.rental.customexception.InvalidMovieIdException;
import com.movie.rental.model.Customer;
import com.movie.rental.model.Movie;
import com.movie.rental.model.MovieRental;

@Service
public class MovieRentalService {
	private static final Logger logger = LoggerFactory.getLogger(MovieRentalService.class);

	public String getRentalInfo(Customer customer) {

		logger.info("Get rental information" + customer);
		// check whether customer details are valid or not by name and rental list
		if (customer.getRentals() == null || customer.getRentals().isEmpty() || 
			    customer.getName() == null || customer.getName().trim().isEmpty() ||
			    !customer.getName().matches("^[a-zA-Z]+$")) {
			    throw new InvalidCustomerException(MovieRentalConstants.INVALID_CUSTOMER_DETAILS);
			}

		HashMap<String, Movie> movies = new HashMap<String, Movie>();

		movies.put("F001", new Movie("You've Got Mail", MovieRentalConstants.REGULAR));
		movies.put("F002", new Movie("Matrix", MovieRentalConstants.REGULAR));
		movies.put("F003", new Movie("Cars", MovieRentalConstants.CHILDRENS));
		movies.put("F004", new Movie("Fast & Furious X", MovieRentalConstants.NEW));

		double totalAmount = 0;

		int frequentEnterPoints = 0;

		String result = MovieRentalConstants.RENTAL_RECORD + customer.getName() + "\n";

		for (MovieRental rentals : customer.getRentals()) {
			// Checking MovieId is valid or not.

			if (movies.get(rentals.getMovieId()) == null) {
				throw new InvalidMovieIdException(MovieRentalConstants.INVALID_MOVIEID);
			}
			Movie movie = movies.get(rentals.getMovieId());
			int rentDays = rentals.getDays();
			
			//check for the valid rental days
			if (rentDays <= 0) {
				throw new InvalidRentDaysException(MovieRentalConstants.INVALID_RENT_DAYS);
			}
			double thisAmount = calculateRent(movie, rentDays);

			// add frequent bonus points
			frequentEnterPoints++;
			// add bonus for a two day new release rental
			if (movies.get(rentals.getMovieId()).getCode() == MovieRentalConstants.NEW && rentals.getDays() > 2)
				frequentEnterPoints++;

			// print figures for this rental
			result += "\t" + movies.get(rentals.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
			totalAmount = totalAmount + thisAmount;
		}
		// add footer lines
		result += MovieRentalConstants.AMOUNT_OWED + totalAmount + "\n" + MovieRentalConstants.EARNED
				+ frequentEnterPoints + MovieRentalConstants.FREQUENCY_POINTS;
		return result;
	}

	/**
	 * Calculate Rent
	 * 
	 * @param movie
	 * @param days
	 * @return double
	 */
	private double calculateRent(Movie movie, int days) {
		logger.info("calculate rental information" + movie, +days);
		double rent = 0.0d;

		switch (movie.getCode()) {
		case MovieRentalConstants.REGULAR:
			rent = 2;
			if (days > 2) {
				rent = ((days - 2) * 1.5) + rent;
			}
			break;
		case MovieRentalConstants.NEW:
			rent = days * 3;
			break;
		case MovieRentalConstants.CHILDRENS:
			rent = 1.5;
			if (days > 3) {
				rent = ((days - 3) * 1.5) + rent;
			}
			break;
		default:
			throw new InvalidMovieIdException(MovieRentalConstants.INVALID_MOVIEID);
		}
		return rent;
	}
}
