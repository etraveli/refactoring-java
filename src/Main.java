import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		String expected = "Rental Record for Ramakrishnan\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
		
		// In case if expected is NULL
		if (expected == null) {
			throw new NullPointerException("Expected String is NULL");
		}

		AddMovie();

		String result = new RentalInfo().statement(
				new Customer("Ramakrishnan", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

		if (!result.equals(expected)) {
			throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected)
					+ System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
		}

		System.out.println("Success");
	}

	/*
	 * Seperated Adding Movie List from Rental Info and 
	 * encapsulated in Movie Class
	 */
	private static void AddMovie() {
		Movie.addMovie("F001", "You've Got Mail", MovieCode.REGULAR);
		Movie.addMovie("F002", "Matrix", MovieCode.REGULAR);
		Movie.addMovie("F003", "Cars", MovieCode.CHILDRENS);
		Movie.addMovie("F004", "Fast & Furious X", MovieCode.NEW);
	}
}
