package service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import exception.MovieCodeNotFoundException;
import model.Customer;
import model.Movie;
import model.Movie.MovieCode;
import model.MovieRental;

/**
 * @class RentalInfo
 * 
 * MISSION: service which provides the information slip.
 */
public class RentalInfoService {
	
	private MovieService movieService;
	
	private final String FORMAT_MOVIE = "\t%s\t%.1f\n";
	private final String FORMAT_HEADER = "Rental Record for %s\n";
	private final String FORMAT_FOOTER_AMOUNT = "Amount owed is %.1f\n";
	private final String FORMAT_FOOTER_FREQUENT_POINTS = "You earned %d frequent points\n";
	
	/*
	 * CONSTRUCTOR
	 */
	public RentalInfoService() {
		movieService = new MovieService();
	}

	/*
	 * PUBLIC METHODS
	 */
	public String statement(Customer customer) {
		Map<String, Movie> movies = movieService.getMovies();
		List<MovieRental> rentals = customer.getRentals();
		double totalAmount = 0;
		int frequentEnterPoints = 0;
		
		String result = formatHeader( customer.getName() );
		
		if( !Objects.isNull(rentals) ) {
			//	cycle over rentals
			for (MovieRental rental : rentals) {
				//	rental data
				Movie movie = movies.get(rental.getMovieId());
				int days = rental.getDays();
				
				//	calculate amount
				double thisAmount = calculateAmount( movie, days );
	
				//	add frequent bonus points
				frequentEnterPoints++;
				
				//	add bonus for a two day new release rental
				if (movie.getCode().equals(MovieCode.NEW) && days > 2)
					frequentEnterPoints++;
	
				//	print figures for this rental
				result += formatMovie( movie.getTitle(), thisAmount );
				totalAmount = totalAmount + thisAmount;
			}
		}
		
		//	add footer lines
		result += formatFooterAmount( totalAmount );
		result += formatFooterFrequentPoints( frequentEnterPoints );

		return result;
	}

	/*
	 * PRIVATE METHODS
	 */
	/***
	 * calculates the amount for a movie for rental days.
	 */
	private double calculateAmount( Movie movie, int days ) {
		double amount = 0.0d;
		
		switch( movie.getCode() ) {
			case REGULAR:
				amount = 2;
				if (days > 2) {
					amount = ((days - 2) * 1.5) + amount;
				}
			break;
			case NEW:
				amount = days * 3;
			break;
			case CHILDREN:
				amount = 1.5;
				if (days > 3) {
					amount = ((days - 3) * 1.5) + amount;
				}
			break;
			default:
				throw new MovieCodeNotFoundException();
		}
		
		return amount;
	}
	
	/***
	 * defines the format for the movie line.
	 */
	private String formatMovie( String title, double amount ) {
		return String.format(FORMAT_MOVIE, title, amount);
	}
	
	/***
	 * defines the format for the header.
	 */
	private String formatHeader( String name ) {
		return String.format(FORMAT_HEADER, name);
	}
	
	/***
	 * defines the format for the footer in amount section.
	 */
	private String formatFooterAmount( double amount ) {
		return String.format(FORMAT_FOOTER_AMOUNT, amount);
	}
	
	/***
	 * defines the format for the footer in amount section.
	 */
	private String formatFooterFrequentPoints( int frequentEnterPoints ) {
		return String.format(FORMAT_FOOTER_FREQUENT_POINTS, frequentEnterPoints);
	}
}
