package com.mithwick93.refactoring.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RentalInfoTest {
    private RentalInfo rentalInfo;

    @BeforeEach
    void setUp() {
        rentalInfo = new RentalInfo();
    }

    @Test
    void givenNullCustomer_whenStatement_thenThrowNullPointerException() {
        Customer customer = null;
        assertThrows(NullPointerException.class, () -> rentalInfo.statement(customer));
    }

    @Test
    void givenInvalidMovieRental_whenStatement_thenThrowNullPointerException() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F005", 3));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        assertThrows(NullPointerException.class, () -> rentalInfo.statement(customer));
    }

    @Test
    void givenCustomerWithNoRentalsAndNoName_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = new ArrayList<>();
        Customer customer = new Customer("", movieRentals);

        String expected = """
                Rental Record for\s
                Amount owed is 0.0
                You earned 0 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithNoRentals_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = new ArrayList<>();
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                Amount owed is 0.0
                You earned 0 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyRegularRentals_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(
                new MovieRental("F001", 3),
                new MovieRental("F002", 1)
        );
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	You've Got Mail	3.5
                	Matrix	2.0
                Amount owed is 5.5
                You earned 2 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyRegularRentalsWithDaysLessThanTwo_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F002", 1));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	Matrix	2.0
                Amount owed is 2.0
                You earned 1 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyRegularRentalsWithDaysEqualToTwo_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F002", 2));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	Matrix	2.0
                Amount owed is 2.0
                You earned 1 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyRegularRentalsWithDaysMoreThanTwo_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F001", 5));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	You've Got Mail	6.5
                Amount owed is 6.5
                You earned 1 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyNewRentalsWithDaysLessThanTwo_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F004", 1));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	Fast & Furious X	3.0
                Amount owed is 3.0
                You earned 1 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyNewRentalsWithDaysEqualToTwo_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F004", 2));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	Fast & Furious X	6.0
                Amount owed is 6.0
                You earned 1 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyNewRentalsWithDaysMoreThanTwo_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F004", 4));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	Fast & Furious X	12.0
                Amount owed is 12.0
                You earned 2 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyChildrenRentalsWithDaysLessThanThree_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F003", 2));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	Cars	1.5
                Amount owed is 1.5
                You earned 1 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyChildrenRentalsWithDaysEqualToThree_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F003", 3));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	Cars	1.5
                Amount owed is 1.5
                You earned 1 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }

    @Test
    void givenCustomerWithOnlyChildrenRentalsWithDaysMoreThanThree_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(new MovieRental("F003", 6));
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	Cars	6.0
                Amount owed is 6.0
                You earned 1 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }


    @Test
    void givenCustomerWithManyRentalTypes_whenStatement_thenReturnCorrectStatement() {
        List<MovieRental> movieRentals = List.of(
                new MovieRental("F001", 2),
                new MovieRental("F003", 3),
                new MovieRental("F004", 7)
        );
        Customer customer = new Customer("C. U. Stomer", movieRentals);

        String expected = """
                Rental Record for C. U. Stomer
                	You've Got Mail	2.0
                	Cars	1.5
                	Fast & Furious X	21.0
                Amount owed is 24.5
                You earned 4 frequent points
                """;

        String result = rentalInfo.statement(customer);

        assertEquals(expected, result);
    }
}
