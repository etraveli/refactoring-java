package com.example.rentalapi.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.rentalapi.constants.RentalConstants;
import com.example.rentalapi.exception.InvalidCustomerException;
import com.example.rentalapi.exception.InvalidMovieException;
import com.example.rentalapi.model.Customer;
import com.example.rentalapi.model.Movie;
import com.example.rentalapi.model.MovieRental;
import com.example.rentalapi.service.MovieService;

@ExtendWith(MockitoExtension.class)
public class RentalServiceImplTest {

	@Mock
    private MovieService movieService;

    @InjectMocks
    private RentalServiceImpl rentalService;

    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_VALID_CUSTOMER)
    void generateRentalStatement_WithValidCustomer_ShouldGenerateStatement() {
        Customer customer = new Customer("John Doe");
        Movie movie1 = new Movie("Movie1", "regular");
        Movie movie2 = new Movie("Movie2", "new");
        Movie movie3 = new Movie("Movie3", "childrens");
        Movie movie4 = new Movie("Movie4", "new");
        MovieRental rental1 = new MovieRental("Movie1", 3);
        MovieRental rental2 = new MovieRental("Movie2", 3);
        MovieRental rental3 = new MovieRental("Movie3", 4);
        MovieRental rental4 = new MovieRental("Movie4", 1);
        
        when(movieService.getAllMovies()).thenReturn(Map.of("Movie1", movie1, "Movie2", movie2, "Movie3", movie3, "Movie4", movie4));
        customer.setRentals(Arrays.asList(rental1, rental2, rental3,rental4));
        String result = rentalService.generateRentalStatement(customer);
        assertNotNull(result);
        assertTrue(result.contains("Rental Record for John Doe"));
        assertTrue(result.contains("\tMovie1\t3.5"));
        assertTrue(result.contains("\tMovie2\t9.0"));
        assertTrue(result.contains("\tMovie3\t3.0"));
        assertTrue(result.contains("\tMovie4\t3.0"));
        assertTrue(result.contains("Amount owed is 18.5"));
        assertTrue(result.contains("You earned 5 frequent points"));
    }

    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_NULL_CUSTOMER)
    void generateRentalStatement_WithNullCustomer_ShouldThrowException() {
        Customer customer = null;
        assertThrows(InvalidCustomerException.class, () -> rentalService.generateRentalStatement(customer));
    }

    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_INVALID_MOVIE)
    void generateRentalStatement_WithInvalidMovie_ShouldThrowException() {
        Customer customer = new Customer("John Doe");
        MovieRental rental = new MovieRental("InvalidMovie", 3);
        customer.setRentals(List.of(rental));
        when(movieService.getAllMovies()).thenReturn(Map.of());
        InvalidMovieException exception = assertThrows(InvalidMovieException.class, () -> rentalService.generateRentalStatement(customer));
        assertEquals(RentalConstants.INVALID_MOVIE_EXCEPTION_MESSAGE, exception.getMessage());
    }

}
