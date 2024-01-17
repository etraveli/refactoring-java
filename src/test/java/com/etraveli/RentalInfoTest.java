package com.etraveli;

import com.etraveli.model.Customer;
import com.etraveli.repository.CustomerRepository;
import com.etraveli.repository.InitialCustomerRepository;
import com.etraveli.repository.InitialMovieRepository;
import com.etraveli.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RentalInfoTest {
    static MovieRepository movieRepository;
    static CustomerRepository customerRepository;

    @BeforeAll
    static void setup(){
        movieRepository = new InitialMovieRepository();
        customerRepository = new InitialCustomerRepository();
    }

    @Test
    void regularMoviesTest(){
        // Arrange
        String expectedRentalInfo = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
        RentalInfo rentalInfo = new RentalInfo(movieRepository);

        // Act
        Customer stomerCustomer = customerRepository.getCustomerById("C001");
        String rentalInfoResult = rentalInfo.statement(stomerCustomer);

        // Assert
        Assertions.assertEquals(expectedRentalInfo, rentalInfoResult);
    }

    @Test
    void childrenMoviesTest(){
        // Arrange
        String expectedRentalInfo = "Rental Record for Children Customer\n\tCars\t7.5\n\tCars\t1.5\nAmount owed is 9.0\nYou earned 2 frequent points\n";
        RentalInfo rentalInfo = new RentalInfo(movieRepository);

        // Act
        Customer stomerCustomer = customerRepository.getCustomerById("C002");
        String rentalInfoResult = rentalInfo.statement(stomerCustomer);

        // Assert
        Assertions.assertEquals(expectedRentalInfo, rentalInfoResult);
    }

    @Test
    void newMoviesTest(){
        // Arrange
        String expectedRentalInfo = "Rental Record for News Customer\n\tFast & Furious X\t3.0\n\tFast & Furious X\t9.0\nAmount owed is 12.0\nYou earned 3 frequent points\n";
        RentalInfo rentalInfo = new RentalInfo(movieRepository);

        // Act
        Customer stomerCustomer = customerRepository.getCustomerById("C003");
        String rentalInfoResult = rentalInfo.statement(stomerCustomer);

        // Assert
        Assertions.assertEquals(expectedRentalInfo, rentalInfoResult);
    }

    @Test
    void mixMoviesTest(){
        // Arrange
        String expectedRentalInfo = "Rental Record for Mix Customer\n\tYou've Got Mail\t2.0\n\tMatrix\t2.0\n\tCars\t1.5\n\tFast & Furious X\t12.0\nAmount owed is 17.5\nYou earned 5 frequent points\n";
        RentalInfo rentalInfo = new RentalInfo(movieRepository);

        // Act
        Customer stomerCustomer = customerRepository.getCustomerById("C004");
        String rentalInfoResult = rentalInfo.statement(stomerCustomer);

        // Assert
        Assertions.assertEquals(expectedRentalInfo, rentalInfoResult);
    }
}
