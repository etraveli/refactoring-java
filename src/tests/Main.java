package tests;

import model.Customer;
import service.RentalInfo;

public class Main {

  public static void main(String[] args) {
    try {
      movieRentalTest();
    } catch (IllegalArgumentException e) {
      System.err.println("Error in application");
      e.printStackTrace();
    }
  }

  public static void movieRentalTest() throws IllegalArgumentException {
    String expected = "Rental Record for C. U. Stomer\n"
        + "\tYou've Got Mail\t3.5\n"
        + "\tMatrix\t2.0\n"
        + "Amount owed is 5.5\n"
        + "You earned 2 frequent points\n";

    final Customer customer = new Customer("C. U. Stomer");
    customer.rent("F001", 3);
    customer.rent("F002", 1);

    String result = new RentalInfo().statement(customer);

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected)
          + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}