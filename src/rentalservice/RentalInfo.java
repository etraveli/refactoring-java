package rentalservice;

import domain.Customer;
import domain.Movie;
import domain.MovieRental;
import movieservice.MovieService;

public class RentalInfo {

	private double totalAmount = 0;
	private int frequentRenterPoints = 0;
	private MovieService movies = MovieService.getInstance();
	private PricingService pricing = PricingService.getInstance();

	private void resetCounters() {
		totalAmount = 0;
		frequentRenterPoints = 0;
	}

	private int getFrequentRenterPointsFor(String category, int days) {
		int points = 1;

		// add bonus for a two day new release rental
		if (category.equals("new") && days > 2) {
			points++;
		}

		return points;
	}

	public String statement(Customer customer) throws IllegalArgumentException {

		resetCounters();

		String result = "Rental Record for " + customer.getName() + "\n";

		for (MovieRental rental : customer.getRentals()) {
			Movie rentedMovie = movies.getMovieById(rental.getMovieId());

			// determine amount for each movie
			double amount = pricing.getPriceFor(rental);

			frequentRenterPoints += getFrequentRenterPointsFor(rentedMovie.getCategory(), rental.getDays());

			// print figures for this rental
			result += "\t" + rentedMovie.getTitle() + "\t" + amount + "\n";
			totalAmount = totalAmount + amount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent points\n";

		return result;
	}
}
