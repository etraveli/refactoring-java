import di.DaggerMovieComponent;
import java.util.Arrays;
import model.Customer;
import model.MovieRental;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.RentalService;

public class MovieRentalTest {
  private static final Logger logger = Logger.getLogger(MovieRentalTest.class);
  private static RentalService rentalService = null;

  @BeforeAll
  public static void init() {
    rentalService = DaggerMovieComponent.create().buildRentalService();
  }


  @Test
  public void testMovieRentalStatement() {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    String result = rentalService.getStatement(new Customer("C. U. Stomer",
        Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

    if (!result.equals(expected)) {
      throw new AssertionError(
          "Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator()
              + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }
    logger.info("Test success");
  }
}
