package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import domain.MovieRental;
import rentalservice.PricingService;

public class PricingServiceTests {

	private PricingService pricing = PricingService.getInstance();
	
	@Test
	public void testRegularPrice() {
		
		MovieRental rental = new MovieRental("F001", 1);
		
		assertTrue(pricing.getPriceFor(rental) > 0, "");
	}

	@Test
	public void testNewPrice() {
		MovieRental rental = new MovieRental("F004", 1);
		
		assertTrue(pricing.getPriceFor(rental) > 0, "");
	}

	@Test
	public void testChildrensPrice() {
		MovieRental rental = new MovieRental("F003", 1);
		
		assertTrue(pricing.getPriceFor(rental) > 0, "");
	}

	@Test
	public void testUnknownPrice() {
		MovieRental rental = new MovieRental("T001", 1);
		
		assertThrows(IllegalArgumentException.class, () -> pricing.getPriceFor(rental));
	}
}
