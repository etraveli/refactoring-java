package com.movierentals;


import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer" + System.lineSeparator()
                    + "\tYou've Got Mail\t3.5" + System.lineSeparator()
                    + "\tMatrix\t2.0" + System.lineSeparator()
                    + "Amount owed is 5.5" + System.lineSeparator()
                    + "You earned 2 frequent points" + System.lineSeparator();

    String result = new RentalInfo().statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
