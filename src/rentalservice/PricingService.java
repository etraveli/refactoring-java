package rentalservice;

import java.security.InvalidAlgorithmParameterException;

import domain.Movie;
import domain.MovieRental;
import movieservice.MovieService;

public class PricingService {
	private static PricingService instance;
	private MovieService movies = MovieService.getInstance();

	public static PricingService getInstance() {
		if (instance == null) {
			instance = new PricingService();
		}
		return instance;
	}

	private PricingService() {
	}

	public double getPriceFor(MovieRental rental) throws InvalidAlgorithmParameterException {
		Movie rentedMovie = movies.getMovieById(rental.getMovieId());

		if (rentedMovie.getCategory().equals("regular")) {
			return regularPricing(rental.getDays());
		}

		if (rentedMovie.getCategory().equals("new")) {
			return newPricing(rental.getDays());
		}

		if (rentedMovie.getCategory().equals("childrens")) {
			return childrensPricing(rental.getDays());
		}
		
		throw new InvalidAlgorithmParameterException("Could not determine price for movie: " + rental.getMovieId());
	}
	
	private double regularPricing(int days) {
		double baseprice = 2;

		if (days > 2) {
			return ((days - 2) * 1.5) + baseprice;
		}

		return baseprice;
	}

	private double newPricing(int days) {
		return days * 3;
	}

	private double childrensPricing(int days) {
		double baseprice = 1.5;
		if (days > 3) {
			return ((days - 3) * 1.5) + baseprice;
		}
		return baseprice;
	}

}
