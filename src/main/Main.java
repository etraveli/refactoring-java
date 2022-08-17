package main;
import java.util.Arrays;

import model.Customer;
import model.MovieRental;
import service.RentalInfoService;

/**
 * @class Main
 * 
 * MISSION: it creates an information slip about movie rentals.
 */
public class Main {
	
	private final Customer CUSTOMER_TEST = new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1)));
	private final String CUSTOMER_TEST_STATEMENT = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

	/*
	 * MAIN
	 */
	public static void main(String[] args) {
		new Main().execute();
	}

	/*
	 * PRIVATE METHODS
	 */
	private void execute() {
		String expected = CUSTOMER_TEST_STATEMENT;
		String result = new RentalInfoService().statement( CUSTOMER_TEST );

		if (!result.equals(expected)) {
			throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
		}

		System.out.println("Success");
	}
}
