package service;

import java.util.Map;

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

		double totalAmount = 0;
		int frequentEnterPoints = 0;
		String result = "Rental Record for " + customer.getName() + "\n";
		for (MovieRental r : customer.getRentals()) {
			double thisAmount = 0;

			//	determine amount for each movie
			if (movies.get(r.getMovieId()).getCode().equals(MovieCode.REGULAR)) {
				thisAmount = 2;
				if (r.getDays() > 2) {
					thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
				}
			}
			if (movies.get(r.getMovieId()).getCode().equals(MovieCode.NEW)) {
				thisAmount = r.getDays() * 3;
			}
			if (movies.get(r.getMovieId()).getCode().equals(MovieCode.CHILDREN)) {
				thisAmount = 1.5;
				if (r.getDays() > 3) {
					thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
				}
			}

			//	add frequent bonus points
			frequentEnterPoints++;
			//	add bonus for a two day new release rental
			if (movies.get(r.getMovieId()).getCode().equals(MovieCode.NEW) && r.getDays() > 2)
				frequentEnterPoints++;

			//	print figures for this rental
			result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
			totalAmount = totalAmount + thisAmount;
		}
		//	add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentEnterPoints + " frequent points\n";

		return result;
	}
}
