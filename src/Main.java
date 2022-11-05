import models.Customer;
import models.MovieRental;

import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    Customer customer = new Customer("C. U. Stomer","CUS");
    List<MovieRental> movieRentals = Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1));
    Provider.getInstance().getRentalLedger().rentToCustomer(movieRentals, customer);

    String result = new RentalInfo().statement(customer);

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }

}
