import java.util.Arrays;

/**
 * We create all of our instances in this class
 * I have added few more test cases with various inputs.
**/
public class Main {

	public static void main(String[] args) {
	 
		// Populate Movie Database
		MovieDataStorage.addMovie(new Movie("F001", "You've Got Mail", Code.REGULAR));
		MovieDataStorage.addMovie(new Movie("F002", "Matrix", Code.REGULAR));
		MovieDataStorage.addMovie(new Movie("F003", "Cars", Code.CHILDREN));
		MovieDataStorage.addMovie(new Movie("F004", "Fast & Furious X", Code.NEW));
		MovieDataStorage.addMovie(new Movie("F004", "Spiderman", Code.REGULAR));

		testGivenCase();
		testInvalidMovie();
		testCustomerIsNull();
		testMovieRentalIsNull();
		
		System.out.println("Success");
	}
  
	// This case was given
	public static void testGivenCase() {
		String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

		String result = RentalInfo.statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));
	    
		checkSuccess(expected, result);  
	}
  
	// When one or some of the movies are invalid. 
	// Statement is created with remaining valid movies.
	public static void testInvalidMovie() {
		String expected = "Rental Record for C. U. Stomer\n\tMatrix\t2.0\nAmount owed is 2.0\nYou earned 1 frequent points\n";

		String result = RentalInfo.statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F008", 3), new MovieRental("F002", 1))));
	    
		checkSuccess(expected, result);
	  
	}
  
	//When customer is null
	public static void testCustomerIsNull() {
		String expected = "Error";

		String result = RentalInfo.statement(null);
			  
		checkSuccess(expected, result);
	}
  
	//When rental info are null
	public static void testMovieRentalIsNull() {
		String expected = "Error";

		String result = RentalInfo.statement(new Customer("C. U. Stomer", null));
			  
		checkSuccess(expected, result);
	}
  
	public static void checkSuccess(String expected, String result) { 
		if (!result.equals(expected)) {
			throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
		}
	}
}
