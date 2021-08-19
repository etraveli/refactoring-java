package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidAlgorithmParameterException;

import org.junit.jupiter.api.Test;

import domain.Movie;
import domain.MovieRental;
import movieservice.MovieService;
import rentalservice.PricingService;

public class PricingServiceTests {

	private PricingService pricing = PricingService.getInstance();
	private MovieService movies = MovieService.getInstance();

	@Test
	public void testRegularPrice() throws InvalidAlgorithmParameterException {
		
		Movie movie = movies.getMovieById("F001");
		MovieRental rental = new MovieRental("F001", 1);
		
		assertTrue(pricing.getPriceFor(rental) > 0, "");
	}

	@Test
	public void testNewPrice() throws InvalidAlgorithmParameterException {
		Movie movie = movies.getMovieById("F004");
		MovieRental rental = new MovieRental("F004", 1);
		
		assertTrue(pricing.getPriceFor(rental) > 0, "");
	}

	@Test
	public void testChildrensPrice() throws InvalidAlgorithmParameterException {
		Movie movie = movies.getMovieById("F003");
		MovieRental rental = new MovieRental("F003", 1);
		
		assertTrue(pricing.getPriceFor(rental) > 0, "");
	}

	@Test
	public void testUnknownPrice() {
		Movie movie = movies.getMovieById("T001");
		MovieRental rental = new MovieRental("T001", 1);
		
		assertThrows(InvalidAlgorithmParameterException.class, () -> pricing.getPriceFor(rental));
	}
}
