package service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import exception.MovieCodeNotFoundException;
import exception.MovieNotFoundException;
import exception.NoCustomerException;
import formatter.SlipFormatter;
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
	private SlipFormatter slipFormatter;
	
	/*
	 * CONSTRUCTOR
	 */
	public RentalInfoService() {
		movieService = new MovieService();
		slipFormatter = SlipFormatter.getInstance();
	}

	/*
	 * PUBLIC METHODS
	 */
	public String statement(Customer customer) {
		if( Objects.isNull(customer) )
			throw new NoCustomerException();
		
		Map<String, Movie> movies = movieService.getMovies();
		List<MovieRental> rentals = customer.getRentals();
		double totalAmount = 0;
		int frequentEnterPoints = 0;
		
		String result = slipFormatter.formatHeader( customer.getName() );
		
		if( !Objects.isNull(rentals) ) {
			//	cycle over rentals
			for (MovieRental rental : rentals) {
				//	rental data
				Movie movie = movies.get(rental.getMovieId());
				int days = rental.getDays();
				
				//	verify the if the movie exists
				if( Objects.isNull(movie) )
					throw new MovieNotFoundException();
				
				//	calculate amount
				double thisAmount = calculateAmount( movie, days );
	
				//	add frequent bonus points
				frequentEnterPoints++;
				
				//	add bonus for a two day new release rental
				if (movie.getCode().equals(MovieCode.NEW) && days > 2)
					frequentEnterPoints++;
	
				//	print figures for this rental
				result += slipFormatter.formatMovie( movie.getTitle(), thisAmount );
				totalAmount = totalAmount + thisAmount;
			}
		}
		
		//	add footer lines
		result += slipFormatter.formatFooterAmount( totalAmount );
		result += slipFormatter.formatFooterFrequentPoints( frequentEnterPoints );
		
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
}
