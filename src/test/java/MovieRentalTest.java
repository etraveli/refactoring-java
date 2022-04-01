import di.DaggerMovieComponent;
import java.util.Arrays;
import model.Customer;
import model.MovieRental;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.RentalService;

public class MovieRentalTest {

  private static RentalService rentalService = null;

  @BeforeAll
  public static void init() {
    rentalService = DaggerMovieComponent.create().buildRentalService();
  }

  @Test
  @DisplayName("Test Rental statement for regular movie types only")
  public void testMovieRentalStatementForRegular() {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    var movieRental1 = MovieRental.builder().movieId("F001").days(3).build();
    var movieRental2 = MovieRental.builder().movieId("F002").days(1).build();
    Customer customer = Customer.builder().name("C. U. Stomer")
        .rentals(Arrays.asList(movieRental1, movieRental2)).build();

    String result = rentalService.getStatement(customer);

    Assertions.assertEquals(expected, result);
  }

  @Test
  @DisplayName("Test Rental statement for all movie types")
  public void testForAllMovieTypes() {
    String expected = "Rental Record for Emma\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\n\tCars\t3.0\n\tFast & Furious X\t9.0\nAmount owed is 17.5\nYou earned 5 frequent points\n";
    var movieRental1 = MovieRental.builder().movieId("F001").days(3).build();
    var movieRental2 = MovieRental.builder().movieId("F002").days(1).build();
    var movieRental3 = MovieRental.builder().movieId("F003").days(4).build();
    var movieRental4 = MovieRental.builder().movieId("F004").days(3).build();
    Customer customer = Customer.builder().name("Emma")
        .rentals(Arrays.asList(movieRental1, movieRental2, movieRental3, movieRental4)).build();

    String result = rentalService.getStatement(customer);

    Assertions.assertEquals(expected, result);
  }

  @Test
  @DisplayName("Test Rental statement for no customer name scenario")
  public void testWithoutCustomerName() {
    String expected = "Invalid Customer or customer name";
    String result = rentalService.getStatement(null);
    Assertions.assertEquals(expected, result);
  }

  @Test
  @DisplayName("Test Rental statement for invalid movie id scenario")
  public void testNotFoundMovies() {
    String expected = "Rental Record for Emma\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\n\tNo movies found for id: F005\n\tFast & Furious X\t9.0\nAmount owed is 14.5\nYou earned 4 frequent points\n";
    var movieRental1 = MovieRental.builder().movieId("F001").days(3).build();
    var movieRental2 = MovieRental.builder().movieId("F002").days(1).build();
    var movieRental3 = MovieRental.builder().movieId("F005").days(4).build();
    var movieRental4 = MovieRental.builder().movieId("F004").days(3).build();
    Customer customer = Customer.builder().name("Emma")
        .rentals(Arrays.asList(movieRental1, movieRental2, movieRental3, movieRental4)).build();

    String result = rentalService.getStatement(customer);

    Assertions.assertEquals(expected, result);
  }

}
