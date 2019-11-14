package com.movierentals;


import com.movierentals.domain.Customer;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    final String expected = "Rental Record for C. U. Stomer" + System.lineSeparator()
                    + "\tYou've Got Mail\t3.5" + System.lineSeparator()
                    + "\tMatrix\t2.0" + System.lineSeparator()
                    + "Amount owed is 5.5" + System.lineSeparator()
                    + "You earned 2 frequent points" + System.lineSeparator();

    final String result = new RentalInfo().statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

    if (!expected.equals(result)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + expected + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
