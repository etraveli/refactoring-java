package com.example.rentalapi.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentalapi.constants.RentalConstants;
import com.example.rentalapi.exception.InvalidCustomerException;
import com.example.rentalapi.exception.InvalidMovieException;
import com.example.rentalapi.model.Customer;
import com.example.rentalapi.model.Movie;
import com.example.rentalapi.model.MovieRental;
import com.example.rentalapi.service.MovieService;
import com.example.rentalapi.service.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private MovieService movieService;

	@Override
	public String generateRentalStatement(Customer customer) {
	    validateCustomer(customer);

	    Map<String, Movie> movies = movieService.getAllMovies();
	    List<MovieRental> rentals = Objects.requireNonNull(customer.getRentals());
	    AtomicReference<Double> totalAmount = new AtomicReference<>(0.0);
	    AtomicInteger frequentRenterPoints = new AtomicInteger(0);
	    StringBuilder rentalDetailsBuilder = processRentals(rentals, movies, totalAmount, frequentRenterPoints);

	    return buildResult(customer.getName(), rentalDetailsBuilder.toString(), totalAmount.get(),
	            frequentRenterPoints.get());
	}

	private void validateCustomer(Customer customer) {
	    if (isInvalidCustomer(customer)) {
	        throw new InvalidCustomerException(RentalConstants.INVALID_CUSTOMER_EXCEPTION_MESSAGE);
	    }
	}

	private StringBuilder processRentals(List<MovieRental> rentals, Map<String, Movie> movies,
	                                     AtomicReference<Double> totalAmount, AtomicInteger frequentRenterPoints) {
	    StringBuilder rentalDetailsBuilder = new StringBuilder();
	    rentals.forEach(rental -> processRental(rental, movies, rentalDetailsBuilder, totalAmount, frequentRenterPoints));
	    return rentalDetailsBuilder;
	}

	private void processRental(MovieRental rental, Map<String, Movie> movies,
	                           StringBuilder rentalDetailsBuilder, AtomicReference<Double> totalAmount,
	                           AtomicInteger frequentRenterPoints) {
	    validateRentalAndMovie(rental, movies);

	    Movie movie = movies.get(rental.getMovieId());
	    double thisAmount = calculateRentalAmount(rental, movie);
	    frequentRenterPoints.addAndGet(calculateFrequentRenterPoints(rental, movie));
	    totalAmount.updateAndGet(value -> value + thisAmount);

	    rentalDetailsBuilder.append(
	            String.format(RentalConstants.PROCESSED_RENTAL_STATEMENT, movie.getTitle(), thisAmount))
	            .append(System.lineSeparator());
	}

	private void validateRentalAndMovie(MovieRental rental, Map<String, Movie> movies) {
	    if (rental == null || rental.getMovieId() == null || !movies.containsKey(rental.getMovieId())) {
	        throw new InvalidMovieException(RentalConstants.INVALID_MOVIE_EXCEPTION_MESSAGE);
	    }
	}


	private String buildResult(String customerName, String rentalDetails, double totalAmount,
			int frequentRenterPoints) {
		return String.format(RentalConstants.RENTAL_RESULT_FORMAT, RentalConstants.RENTAL_RECORD_INTRO, customerName,
				rentalDetails, RentalConstants.RENTAL_AMOUNT_OWED_IS, totalAmount, RentalConstants.RENTAL_YOU_EARNED,
				frequentRenterPoints, RentalConstants.RENTAL_FREQUENT_POINTS);
	}

	private boolean isInvalidCustomer(Customer customer) {
		return customer == null || customer.getName() == null || customer.getName().isBlank()
				|| customer.getRentals() == null;
	}

	private int calculateFrequentRenterPoints(MovieRental rental, Movie movie) {
		int points = 1;
		if (RentalConstants.MOVIE_CODE_NEW.equals(movie.getCode()) && rental.getDays() > 2) {
			points++;
		}
		return points;
	}

	private double calculateRentalAmount(MovieRental rental, Movie movie) {
		double thisAmount = 0;

		switch (movie.getCode()) {
		case RentalConstants.MOVIE_CODE_REGULAR:
			thisAmount = 2;
			if (rental.getDays() > 2) {
				thisAmount += (rental.getDays() - 2) * 1.5;
			}
			break;
		case RentalConstants.MOVIE_CODE_NEW:
			thisAmount = rental.getDays() * 3;
			break;
		case RentalConstants.MOVIE_CODE_CHILDRENS:
			thisAmount = 1.5;
			if (rental.getDays() > 3) {
				thisAmount += (rental.getDays() - 3) * 1.5;
			}
			break;
		}
		return thisAmount;
	}

}
