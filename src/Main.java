import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
	
		//Initializing customer 
		Customer customer = customerCreation();
		
		//Initializing movies 
		HashMap<String, Movie> movies = moviesCreation();
		
		//Adding movies as function argument to make inputs in a single place
		String result = new RentalInfo().statement(customer, movies);
	
	    if (!result.equals(expected)) {
	    throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
	    }
	
	    System.out.println("Success");
	}

	//Making functions for input for easy readability 
	private static Customer customerCreation() {
		MovieRental movieRental1 = new MovieRental("F001", 3);
		MovieRental movieRental2 = new MovieRental("F002", 1);
		List<MovieRental> movieRentals = Arrays.asList(movieRental1, movieRental2);
		Customer customer = new Customer("C. U. Stomer", movieRentals);
		return customer;
	}
	
	private static HashMap<String, Movie> moviesCreation() {
		HashMap<String, Movie> movies = new HashMap<String, Movie>();
	    movies.put("F001", new Movie("You've Got Mail", "regular"));
	    movies.put("F002", new Movie("Matrix", "regular"));
	    movies.put("F003", new Movie("Cars", "childrens"));
	    movies.put("F004", new Movie("Fast & Furious X", "new"));
		return movies;
	}
	
}
