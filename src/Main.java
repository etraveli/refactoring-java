import model.Customer;
import model.MovieRental;
import repository.impl.InMemoryMapMovieRepository;
import service.RentalService;

import java.util.Arrays;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) {
    RentalService rentalService = new RentalService(new InMemoryMapMovieRepository());

    String expected1 = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
    String result1 = rentalService.statement(new Customer("C. U. Stomer", new HashSet<>(Arrays.asList(
            new MovieRental("F001", 3),
            new MovieRental("F002", 1)))));
    check(expected1, result1);

    String expected2 = "No customer provided.";
    String result2 = rentalService.statement(null);
    check(expected2, result2);

    String expected3 = "No customer rentals provided.";
    String result3 = rentalService.statement(new Customer("John Doe", null));
    check(expected3, result3);

    String expected4 = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tNo movie with ID ABCD found.\nAmount owed is 3.5\nYou earned 1 frequent points\n";
    String result4 = rentalService.statement(new Customer("C. U. Stomer", new HashSet<>(Arrays.asList(
            new MovieRental("F001", 3),
            new MovieRental("ABCD", 1)))));
    check(expected4, result4);

    System.out.println("Success");
  }

  public static void check(String expected, String result) {
    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + expected + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }
  }

}
