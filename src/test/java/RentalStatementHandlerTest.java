import di.DaggerMovieComponent;
import handler.RentalStatementHandler;
import java.util.Arrays;
import model.Customer;
import model.MovieRental;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RentalStatementHandlerTest {
  private static RentalStatementHandler rentalStatementHandler = null;

  @BeforeAll
  public static void init() {
    rentalStatementHandler = DaggerMovieComponent.create().buildRentalStatementHandler();
  }

  @Test
  @DisplayName("Test Rental statement for no customer name scenario")
  public void testWithoutCustomerName() {
    var exception = Assertions.assertThrows(RuntimeException.class, () -> rentalStatementHandler.generateSummary(null));
    Assertions.assertEquals("Invalid Customer or customer name", exception.getMessage());
  }

  @Test
  @DisplayName("Rental statement without any rentals")
  public void testNoMovies() {
    Customer customer = Customer.builder().name("Emma")
        .rentals(null).build();
    var exception = Assertions.assertThrows(RuntimeException.class, () -> rentalStatementHandler.generateSummary(customer));
    Assertions.assertEquals("Invalid Movie Rentals or No movie Rentals", exception.getMessage());
  }

  @Test
  @DisplayName("Test Rental statement for all movie types")
  public void testForAllMovieTypes() {
    String expectedSummary = "Rental Record for Emma\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\n\tCars\t3.0\n\tFast & Furious X\t9.0\nAmount owed is 17.5\nYou earned 5 frequent points\n";
    var movieRental1 = MovieRental.builder().movieId("F001").days(3).build();
    var movieRental2 = MovieRental.builder().movieId("F002").days(1).build();
    var movieRental3 = MovieRental.builder().movieId("F003").days(4).build();
    var movieRental4 = MovieRental.builder().movieId("F004").days(3).build();
    Customer customer = Customer.builder().name("Emma")
        .rentals(Arrays.asList(movieRental1, movieRental2, movieRental3, movieRental4)).build();

    var actualSummary = rentalStatementHandler.generateSummary(customer);

    Assertions.assertEquals(expectedSummary, actualSummary);
  }
}
