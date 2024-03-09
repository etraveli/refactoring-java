package com.mithwick93.refactoring.java;

import com.mithwick93.refactoring.java.entity.Customer;
import com.mithwick93.refactoring.java.entity.MovieRental;
import com.mithwick93.refactoring.java.repositroy.MovieRepository;
import com.mithwick93.refactoring.java.repositroy.impl.MovieRepositoryImpl;

import java.util.Arrays;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        String expected = """
                Rental Record for C. U. Stomer
                	You've Got Mail	3.5
                	Matrix	2.0
                Amount owed is 5.5
                You earned 2 frequent points
                """;

        MovieRepository movieRepository = new MovieRepositoryImpl();
        String result = new RentalInfo(movieRepository)
                .statement(
                        new Customer(
                                "C. U. Stomer",
                                Arrays.asList(
                                        new MovieRental("F001", 3),
                                        new MovieRental("F002", 1))
                        )
                );

        if (!result.equals(expected)) {
            throw new AssertionError(
                    "Expected: "
                            + System.lineSeparator()
                            + String.format(expected)
                            + System.lineSeparator()
                            + System.lineSeparator()
                            + "Got: "
                            + System.lineSeparator()
                            + result
            );
        }

        System.out.println("Success");
    }
}
