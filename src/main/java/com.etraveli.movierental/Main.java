package com.etraveli.movierental;

import com.etraveli.movierental.builder.CustomerBuilder;
import com.etraveli.movierental.util.RentalUtil;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    Customer customer = CustomerBuilder.builder()
            .name("C. U. Stomer")
            .rentals(Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1)))
            .build();

    String result = RentalUtil.createStatement(customer);

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
