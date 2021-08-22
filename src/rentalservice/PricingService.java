package rentalservice;

import domain.Movie;
import domain.MovieRental;
import movieservice.MovieService;
import rentalservice.prices.ChildrensMoviePrice;
import rentalservice.prices.NewMoviePrice;
import rentalservice.prices.Price;
import rentalservice.prices.RegularMoviePrice;

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

	public double getPriceFor(MovieRental rental) throws IllegalArgumentException {
		Movie rentedMovie = movies.getMovieById(rental.getMovieId());

		try {
			Price priceStrategy = getPriceByCategory(rentedMovie.getCategory());
			
			return priceStrategy.getPriceFor(rental.getDays());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Could not determine price for movieId: " + rental.getMovieId());
		}
	}

	private Price getPriceByCategory(String category) throws IllegalArgumentException {
		if (category.equals("regular")) {
			return new RegularMoviePrice();
		}

		if (category.equals("new")) {
			return new NewMoviePrice();
		}

		if (category.equals("childrens")) {
			return new ChildrensMoviePrice();
		}

		throw new IllegalArgumentException("Could not determine price for category: " + category);

	}
}
