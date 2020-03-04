import java.util.List;
import java.util.Optional;

import database.DatabaseClass;
import entities.Customer;
import entities.Movie;
import entities.MovieCode;
import entities.MovieRental;

public class RentalInfo {

	// COMMENTS Moved data handling to seperate class as this layer to be considered as Service
	private DatabaseClass databaseClass;

	public RentalInfo(DatabaseClass databaseClass) {
		this.databaseClass = databaseClass;
	}

	public List<Movie> getAllMovies() {
		return databaseClass.getAllMovies();
	}

	public String statement(Customer customer) {
		double totalAmount = 0;
		int frequentEnterPoints = 0;
		// COMMENT Replacing String concatenation with StringBuilder
		StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + System.lineSeparator());
		for (MovieRental rental : customer.getRentals()) {
			// COMMENT Using Optional as it may or may not exists in DB
			Optional<Movie> movie = databaseClass.getMovieById(rental.getMovieId());
			if (!movie.isPresent()) {
				continue;
			}
			double price = movie.get().getCode() != null ? movie.get().getCode().getRentalRecordPrice(rental.getDays()) : 0;
			if (price != 0) {
				frequentEnterPoints = getFrequentEnterPoints(frequentEnterPoints, movie.get().getCode(), rental.getDays());
			}
			result.append("\t").append(movie.get().getTitle()).append("\t").append(price).append(System.lineSeparator());
			totalAmount = totalAmount + price;
		}
		result.append("Amount owed is ").append(totalAmount).append(System.lineSeparator());
		result.append("You earned ").append(frequentEnterPoints).append(" frequent points").append(System.lineSeparator());

		return result.toString();
	}

	private int getFrequentEnterPoints(int frequentEnterPoints, MovieCode movieCode, int days) {
		return (movieCode.equals(MovieCode.NEW) && days > 2) ? frequentEnterPoints + 2 : frequentEnterPoints + 1;
	}
}
