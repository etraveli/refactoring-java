package com.etraveli.refactoring;

import com.etraveli.refactoring.repository.*;
import com.etraveli.refactoring.service.*;


public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    RentalInfo rentalInfo = new RentalInfo(new MovieDB(), new CustomerDB());
    rentalInfo.createCustomer("C001", "C. U. Stomer");
    rentalInfo.addRental("C001", "F001", 3);
    rentalInfo.addRental("C001", "F002", 1);
    String result = rentalInfo.statement("C001");

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
    System.out.println(result);
  }
}
