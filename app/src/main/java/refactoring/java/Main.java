package refactoring.java;

import java.util.Arrays;

import refactoring.java.config.ApplicationConfiguration;
import refactoring.java.config.ApplicationConfigurationImpl;
import refactoring.java.model.Customer;
import refactoring.java.model.MovieRental;
import refactoring.java.service.LoyaltyPointsCalculatorImpl;
import refactoring.java.service.MovieRepositoryImpl;
import refactoring.java.service.PriceCalculatorImpl;
import refactoring.java.statement.StatementManager;

public class Main {

  public static void main(String[] args) {
    ApplicationConfiguration applicationConfiguration = new ApplicationConfigurationImpl(
            new LoyaltyPointsCalculatorImpl(),
            new MovieRepositoryImpl(),
            new PriceCalculatorImpl()
    );

    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    String result = new StatementManager(applicationConfiguration).createStatement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
