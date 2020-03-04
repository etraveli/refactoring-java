import java.util.Arrays;

import database.DatabaseClass;
import entities.Customer;
import entities.MovieRental;

public class Main {

	public static void main(String[] args) {
		// COMMENTS Introduced System.lineSeparator to run in different OS
		String expected = "Rental Record for C. U. Stomer" + System.lineSeparator() + "\t" + "You've Got Mail" + "\t" + "3.5" + System.lineSeparator()
				+ "\t" + "Matrix" + "\t" + "2.0" + System.lineSeparator() + "\t" + "Inception" + "\t" + "0.0" + System.lineSeparator()
				+ "Amount owed is 5.5" + System.lineSeparator() + "You earned 2 frequent points" + System.lineSeparator();
		// COMMENTS Creating rentalInfo instance so it can be reused
		RentalInfo rentalInfo = new RentalInfo(new DatabaseClass());
		String result = rentalInfo.statement(new Customer("C. U. Stomer",
				Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1), new MovieRental("F005", 5), new MovieRental("F006", 1))));
		if (!result.equals(expected)) {
			// COMMENTS Removing redundant function - String.format
			throw new AssertionError("Expected: " + System.lineSeparator() + expected + System.lineSeparator() + System.lineSeparator() + "Got: "
					+ System.lineSeparator() + result);
		}
		System.out.println("Success");
	}
}
