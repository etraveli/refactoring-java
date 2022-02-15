import java.util.Arrays;

import com.etraveligroup.movierental.models.Customer;
import com.etraveligroup.movierental.models.MovieRental;
import com.etraveligroup.movierental.service.RentalManagerService;
import com.etraveligroup.movierental.serviceimpl.RentalManagerServiceImpl;

/**
 * @author LMOPURI
 *
 */
public class Main {

	public static void main(String[] args) {
		String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 0 frequent points\n";
		RentalManagerService rentalManagerService = new RentalManagerServiceImpl();
		String result = rentalManagerService.getRentalAmoutAndBonusPoints(
				new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

		if (!result.equals(expected)) {
			throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected)
					+ System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
		}

		System.out.println("Success" + result);
	}
}
