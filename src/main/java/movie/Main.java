package movie;

import movie.dto.Customer;
import movie.dto.MovieRental;
import movie.service.RentalInfoService;
import movie.service.impl.RentalInfoServiceImpl;

import java.util.Arrays;

public class Main {

  private static RentalInfoService rentalInfoService = new RentalInfoServiceImpl();

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\t" +
            "You've Got Mail\t3.5\n\t" +
            "Matrix\t2.0\n" +
            "Amount owed is 5.5\n" +
            "You earned 2 frequent points\n";

    String result = rentalInfoService.getStatementForCustomer(
            new Customer("C. U. Stomer",
                    Arrays.asList(
                            new MovieRental("F001", 3),
                            new MovieRental("F002", 1))));

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " +
              System.lineSeparator() +
              expected +
              System.lineSeparator() +
              System.lineSeparator() +
              "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
