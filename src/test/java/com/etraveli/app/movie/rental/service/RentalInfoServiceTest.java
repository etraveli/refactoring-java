package com.etraveli.app.movie.rental.service;

import com.etraveli.app.movie.rental.model.Customer;
import com.etraveli.app.movie.rental.model.MovieRental;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Rental information statement
 */
class RentalInfoServiceTest {

    private RentalInfoService rentalInfoService;

    @BeforeEach
    void setUp() {
        rentalInfoService = new RentalInfoService();
    }

    @AfterEach
    void tearDown() {
        rentalInfoService = null;
    }

    @Test
    @DisplayName("Validate generated statement details")
    void generate_statement() {
        assertNotNull(rentalInfoService);
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
        String result = new RentalInfoService().statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));
        assertEquals(expected, result);
    }
}