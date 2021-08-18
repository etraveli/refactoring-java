package rentalservice;
import domain.Customer;
import domain.Movie;
import domain.MovieRental;
import movieservice.MovieService;

public class RentalInfo {

	private double totalAmount = 0;
	private int frequentRenterPoints = 0;
	private MovieService movies = MovieService.getInstance();
	
	private void resetCounters() {
		totalAmount = 0;
		frequentRenterPoints = 0;
	}

	private Movie getMovieById(String movieId) {
		return movies.get(movieId);
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
	
	private int getFrequentRenterPointsFor(String category, int days) {
		int points = 1;
		
		// add bonus for a two day new release rental
		if (category.equals("new") && days > 2) {
			points ++;
		}
		
		return points;
	}

	public String statement(Customer customer) {
		
		resetCounters();
		
		String result = "Rental Record for " + customer.getName() + "\n";

		for (MovieRental rental : customer.getRentals()) {
			Movie rentedMovie = getMovieById(rental.getMovieId());
			
			double amount = 0;

			// determine amount for each movie
			if (rentedMovie.getCategory().equals("regular")) {
				amount = regularPricing(rental.getDays());
			}
			
			if (rentedMovie.getCategory().equals("new")) {
				amount = newPricing(rental.getDays());
			}
			
			if (rentedMovie.getCategory().equals("childrens")) {
				amount = childrensPricing(rental.getDays());
			}

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
