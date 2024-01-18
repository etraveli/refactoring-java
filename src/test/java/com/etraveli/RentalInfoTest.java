package com.etraveli;

import com.etraveli.model.Customer;
import com.etraveli.repository.CustomerRepository;
import com.etraveli.repository.InitialCustomerRepository;
import com.etraveli.repository.InitialMovieRepository;
import com.etraveli.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RentalInfoTest {
    private static MovieRepository movieRepository;
    private static CustomerRepository customerRepository;

    @BeforeAll
    static void setup() {
        movieRepository = new InitialMovieRepository();
        customerRepository = new InitialCustomerRepository();
    }

    @ParameterizedTest
    @MethodSource("provideCustomersForGettingRentals")
    void regularMoviesTest(String customerId, String expectedRentalInfo) {
        // Arrange
        RentalInfo rentalInfo = new RentalInfo(movieRepository);

        // Act
        Customer stomerCustomer = customerRepository.getCustomerById(customerId);
        String rentalInfoResult = rentalInfo.getRentalInfoForCustomer(stomerCustomer);

        // Assert
        Assertions.assertEquals(expectedRentalInfo, rentalInfoResult);
    }

    private static Stream<Arguments> provideCustomersForGettingRentals() {
        return Stream.of(
                Arguments.of("C001", "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owned is 5.5\nYou earned 2 frequent points\n"),
                Arguments.of("C002", "Rental Record for Children Customer\n\tCars\t7.5\n\tCars\t1.5\nAmount owned is 9.0\nYou earned 2 frequent points\n"),
                Arguments.of("C003", "Rental Record for News Customer\n\tFast & Furious X\t3.0\n\tFast & Furious X\t9.0\nAmount owned is 12.0\nYou earned 3 frequent points\n"),
                Arguments.of("C004", "Rental Record for Mix Customer\n\tYou've Got Mail\t2.0\n\tMatrix\t2.0\n\tCars\t1.5\n\tFast & Furious X\t12.0\nAmount owned is 17.5\nYou earned 5 frequent points\n"),
                Arguments.of("C005", "Rental Record for Skip Customer\n\tMatrix\t5.0\nAmount owned is 5.0\nYou earned 1 frequent points\n")
        );
    }
}
