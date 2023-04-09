package com.etraveliGroup.service;

import com.etraveliGroup.entity.Customer;
import com.etraveliGroup.entity.MovieRental;
import com.etraveliGroup.exception.InvalidCustomerException;
import com.etraveliGroup.exception.InvalidDaysException;
import com.etraveliGroup.exception.InvalidMoveIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RentalInfoServiceTest {
    @InjectMocks
    RentalInfoService rentalInfoService;
    Customer customer = new Customer();
    List<MovieRental> rentals = new ArrayList<>();
    MovieRental movieRental = new MovieRental();
    String expected = "Rental Record for abc\n\tYou've Got Mail\t2.0\nAmount owed is 2.0\nYou earned 1 frequent points\n";
    @BeforeEach
    public void init(){
        customer.setName("abc");
        movieRental.setDays(1);
        movieRental.setMovieId("F001");
        rentals.add(movieRental);
        customer.setRentals(rentals);
    }

    @Test
    public void getRentalInfo() {
        String result = rentalInfoService.getRentalInfo(customer);
        assertEquals(expected,result);
    }
    @Test
    public void getRentalInfoThrowsInvalidCustomerException() {
        customer.setName(null);
        InvalidCustomerException thrown = assertThrows(
                InvalidCustomerException.class,
                () -> rentalInfoService.getRentalInfo(customer),
                ""
        );

        assertTrue(thrown.getMessage().contentEquals("Invalid Customer Details,please enter valid details."));
    }
    @Test
    public void getRentalInfoThrowsInvalidMovieIdException() {
        movieRental.setMovieId("abc");
        rentals.add(movieRental);
        customer.setRentals(rentals);
        InvalidMoveIdException thrown = assertThrows(
                InvalidMoveIdException.class,
                () -> rentalInfoService.getRentalInfo(customer),
                ""
        );

        assertTrue(thrown.getMessage().contentEquals("Movie Id not found,please enter valid MovieId"));
    }
    @Test
    public void getRentalInfoThrowsInvalidDaysException() {
        movieRental.setDays(0);
        rentals.add(movieRental);
        customer.setRentals(rentals);
        InvalidDaysException thrown = assertThrows(
                InvalidDaysException.class,
                () -> rentalInfoService.getRentalInfo(customer),
                ""
        );

        assertTrue(thrown.getMessage().contentEquals("Please enter valid days for rent"));
    }

}
