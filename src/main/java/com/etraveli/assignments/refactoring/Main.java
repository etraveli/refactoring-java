package com.etraveli.assignments.refactoring;

import com.etraveli.assignments.refactoring.model.Customer;
import com.etraveli.assignments.refactoring.model.MovieRental;
import com.etraveli.assignments.refactoring.repository.MovieRepository;
import com.etraveli.assignments.refactoring.service.RentalInfoService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Main runner class.
 */
public class Main {

  private static final Logger LOGGER = LogManager.getLogger(Main.class);

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
public static void main(String[] args) {
    // The expected string
    String expected =
        """
                          Rental Record for C. U. Stomer
                          \tYou've Got Mail\t3.5
                          \tMatrix\t2.0
                          Amount owed is 5.5
                          You earned 2 frequent points
                          """;

    try {
        // The result generated from RentalInfoService implementation.
        String result;
        result =
            new RentalInfoService(new MovieRepository())
                .buildRentalInfoStatement(
                    new Customer(
                        "C. U. Stomer",
                        List.of(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        if (!result.equals(expected)) {
          throw new AssertionError(
              "Expected: "
                  + System.lineSeparator()
                  + String.format(expected)
                  + System.lineSeparator()
                  + System.lineSeparator()
                  + "Got: "
                  + System.lineSeparator()
                  + result);
        }
        LOGGER.info("Success");
    } catch (Exception e) {
        LOGGER.error("Validation failed",e);
    }
  }
}
