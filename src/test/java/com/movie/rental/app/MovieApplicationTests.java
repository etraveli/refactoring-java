package com.movie.rental.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.movie.rental.customexception.InvalidCustomerException;
import com.movie.rental.customexception.InvalidMovieIdException;
import com.movie.rental.customexception.InvalidRentDaysException;
import com.movie.rental.model.Customer;
import com.movie.rental.model.MovieRental;
import com.movie.rental.service.MovieRentalService;

public class MovieApplicationTests {

	private MovieRentalService movieRentalService;

	@BeforeEach
	public void setUp() {
		movieRentalService = new MovieRentalService();
	}

	@Test
	@DisplayName("Test case to check the rentalinfo")
	public void testValidRentalInfo() {
		Customer customer = new Customer();
		List<MovieRental> rentals = new ArrayList<>();
		rentals.add(new MovieRental("F001", 3));
		customer.setRentals(rentals);
		customer.setName("Swathi");
		String rentalInfo = movieRentalService.getRentalInfo(customer);
		
		String expectedRentalInfo = "Rental Record for Swathi\n" +
			    "\tYou've Got Mail\t3.5\n" +
			    "Amount owed is 3.5\n" +
			    "You earned 1 frequent points";
		
		assertEquals(rentalInfo.replaceAll("\\s+",""), expectedRentalInfo.replaceAll("\\s+",""));
		
	}
	
	@Test
	@DisplayName("Test case to check the invalid rentalinfo")
	public void testInValidRentalInfo() {
		Customer customer = new Customer();
		List<MovieRental> rentals = new ArrayList<>();
		rentals.add(new MovieRental("F001", 3));
		customer.setRentals(rentals);
		customer.setName("SwathiNV");
		String rentalInfo = movieRentalService.getRentalInfo(customer);
		String expectedRentalInfo = "Rental Record for Swathi\n";
		assertNotEquals(rentalInfo, expectedRentalInfo);
	}

	@Test
	@DisplayName("Test case to check the valid rental information")
	public void testValidMovieRentalInfo() {

		Customer customer = new Customer();
		customer.setName("Swathi");

		List<MovieRental> rentals = new ArrayList<>();
		rentals.add(new MovieRental("F001", 3));
		rentals.add(new MovieRental("F002", 1));
		rentals.add(new MovieRental("F003", 5));
		rentals.add(new MovieRental("F004", 2));
		customer.setRentals(rentals);

		String rentalInfo = movieRentalService.getRentalInfo(customer);

		assertTrue(rentalInfo.contains("You've Got Mail"));
		assertTrue(rentalInfo.contains("Matrix"));
		assertTrue(rentalInfo.contains("Cars"));
		assertTrue(rentalInfo.contains("Fast & Furious X"));
		assertTrue(rentalInfo.contains("1"));
		assertTrue(rentalInfo.contains("5"));
	}
	
	@Test
	@DisplayName("Test case to check the empty rental list")
	public void testEmptyRentals() {
	    Customer customer = new Customer();
	    customer.setName("Alice");
	    customer.setRentals(new ArrayList<>()); 
	    Assertions.assertThrows(InvalidCustomerException.class,
	            () -> movieRentalService.getRentalInfo(customer));
	}

	@Test
	@DisplayName("Test case to check the empty rental list")
	public void testEmptyCustomerName() {
	    Customer customer = new Customer(); 
	    List<MovieRental> rentals = new ArrayList<>();
	    rentals.add(new MovieRental("F001", 3)); 	    
	    customer.setRentals(rentals);

	    Assertions.assertThrows(InvalidCustomerException.class,
	            () -> movieRentalService.getRentalInfo(customer));
	}

	@Test
	@DisplayName("Test case to check the empty empty customer name")
	public void testInvalidCustomerDetails() {
		Customer customer = new Customer(); // Empty name
		Assertions.assertThrows(InvalidCustomerException.class, () -> movieRentalService.getRentalInfo(customer));
	}

	@Test
	@DisplayName("Test case to check the invalid movie id")
	public void testInvalidMovieId() {
		Customer customer = new Customer();
		List<MovieRental> rentals = new ArrayList<>();
		rentals.add(new MovieRental("INVALID_ID", 3)); // Invalid movie ID
		customer.setRentals(rentals);
		customer.setName("Swathi");
		Assertions.assertThrows(InvalidMovieIdException.class, () -> movieRentalService.getRentalInfo(customer));
	}

	@Test
	@DisplayName("Test case to check the invalid movie id")
	public void testInvalidRentDays() {
		Customer customer = new Customer();
		List<MovieRental> rentals = new ArrayList<>();
		rentals.add(new MovieRental("F002", -1)); // Negative rental days
		customer.setRentals(rentals);
		customer.setName("Nikhil");
		Assertions.assertThrows(InvalidRentDaysException.class, () -> movieRentalService.getRentalInfo(customer));
	}
	
	@Test
	@DisplayName("Test case to check the special character for customer name")
    public void testInvalidNameWithSpecialCharacters() {
        MovieRentalService movieRentalService = new MovieRentalService();
        Customer customer = new Customer();
        customer.setName("Swathi23Nagalapura?Venkatesha");
        Assertions.assertThrows(InvalidCustomerException.class, () -> movieRentalService.getRentalInfo(customer));
	}
	}
