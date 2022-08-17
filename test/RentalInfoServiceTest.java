import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exception.MovieNotFoundException;
import exception.NoCustomerException;
import model.Customer;
import model.MovieRental;
import service.RentalInfoService;

class RentalInfoServiceTest {
	
	private static RentalInfoService service;
	
	@BeforeAll
	static void setUpBeforeClass() {
		service = new RentalInfoService();
	}

	/*
	 * BASE CASES
	 */
	@Test
	void testNoCustomer() {
		NoCustomerException exception = assertThrows(NoCustomerException.class, () -> {
			service.statement(null);
		});
		
		assertEquals("Customer not provided.", exception.getMessage());
	}

	@Test
	void testNoMovies() {
		String result = service.statement(new Customer("John Tolkien", null));
		
		assertEquals("Rental Record for John Tolkien\nAmount owed is 0.0\nYou earned 0 frequent points\n", result);
	}

	@Test
	void testNoExistingMovie() {
		MovieNotFoundException exception = assertThrows(MovieNotFoundException.class, () -> {
			List<MovieRental> rentals = Arrays.asList(new MovieRental("F001", 1), new MovieRental("----", 1));
			service.statement(new Customer("John Tolkien", rentals));
		});
		
		assertEquals("Movie not found.", exception.getMessage());
	}
	
	/*
	 * FREQUENT ENTER POINTS
	 */
	@Test
	void testNoAdditionalFrequentPoint() {
		List<MovieRental> rentals = Arrays.asList(new MovieRental("F004", 2));
		String result = service.statement(new Customer("John Tolkien", rentals));
		
		assertEquals("Rental Record for John Tolkien\n\tFast & Furious X\t6.0\nAmount owed is 6.0\nYou earned 1 frequent points\n", result);
	}
	
	@Test
	void testAdditionalFrequentPoint() {
		List<MovieRental> rentals = Arrays.asList(new MovieRental("F004", 3));
		String result = service.statement(new Customer("John Tolkien", rentals));
		
		assertEquals("Rental Record for John Tolkien\n\tFast & Furious X\t9.0\nAmount owed is 9.0\nYou earned 2 frequent points\n", result);
	}

	/*
	 * NORMAL MOVIES
	 */
	@Test
	void testTwoMovies() {
		List<MovieRental> rentals = Arrays.asList(new MovieRental("F002", 1), new MovieRental("F003", 1));
		String result = service.statement(new Customer("John Tolkien", rentals));
		
		assertEquals("Rental Record for John Tolkien\n\tMatrix\t2.0\n\tCars\t1.5\nAmount owed is 3.5\nYou earned 2 frequent points\n", result);
	}

	@Test
	void testAllMovies() {
		List<MovieRental> rentals = Arrays.asList(new MovieRental("F001", 2), new MovieRental("F002", 2), new MovieRental("F003", 2), new MovieRental("F004", 2));
		String result = service.statement(new Customer("John Tolkien", rentals));
		
		assertEquals("Rental Record for John Tolkien\n\tYou've Got Mail\t2.0\n\tMatrix\t2.0\n\tCars\t1.5\n\tFast & Furious X\t6.0\nAmount owed is 11.5\nYou earned 4 frequent points\n", result);
	}

}
