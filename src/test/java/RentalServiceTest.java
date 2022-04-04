import di.DaggerMovieComponent;
import java.util.Arrays;
import model.Customer;
import model.MovieRental;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.RentalService;

public class RentalServiceTest {
  private static RentalService rentalService = null;

  @BeforeAll
  public static void init() {
    rentalService = DaggerMovieComponent.create().buildRentalService();
  }

  @Test
  @DisplayName("Test Rental result for regular movie types only")
  public void testMovieRentalStatementForRegular() {
    var movieRental1 = MovieRental.builder().movieId("F001").days(3).build();
    var movieRental2 = MovieRental.builder().movieId("F002").days(1).build();
    Customer customer = Customer.builder().name("C. U. Stomer")
        .rentals(Arrays.asList(movieRental1, movieRental2)).build();

    var result = rentalService.getStatement(customer);
    Assertions.assertEquals(5.5, result.getTotalAmount());
    Assertions.assertEquals(2, result.getBonusPoints());
    Assertions.assertEquals("You've Got Mail", result.getResults().get(0).getMovieTitle());
  }

  @Test
  @DisplayName("Test Rental result for all movie types")
  public void testForAllMovieTypes() {
    var movieRental1 = MovieRental.builder().movieId("F001").days(3).build();
    var movieRental2 = MovieRental.builder().movieId("F002").days(1).build();
    var movieRental3 = MovieRental.builder().movieId("F003").days(4).build();
    var movieRental4 = MovieRental.builder().movieId("F004").days(3).build();
    Customer customer = Customer.builder().name("Emma")
        .rentals(Arrays.asList(movieRental1, movieRental2, movieRental3, movieRental4)).build();

    var result = rentalService.getStatement(customer);

    Assertions.assertEquals(17.5, result.getTotalAmount());
    Assertions.assertEquals(5, result.getBonusPoints());
    Assertions.assertEquals("You've Got Mail", result.getResults().get(0).getMovieTitle());
    Assertions.assertEquals("Matrix", result.getResults().get(1).getMovieTitle());
    Assertions.assertEquals("Cars", result.getResults().get(2).getMovieTitle());
    Assertions.assertEquals("Fast & Furious X", result.getResults().get(3).getMovieTitle());

    Assertions.assertEquals(3.0, result.getResults().get(2).getAmount());
    Assertions.assertEquals(3.5, result.getResults().get(0).getAmount());
  }
  
  @Test
  @DisplayName("Test Rental result for invalid movie id scenario")
  public void testNotFoundMovies() {
    var movieRental1 = MovieRental.builder().movieId("F001").days(3).build();
    var movieRental2 = MovieRental.builder().movieId("F002").days(1).build();
    var movieRental3 = MovieRental.builder().movieId("F005").days(4).build();
    var movieRental4 = MovieRental.builder().movieId("F004").days(3).build();
    Customer customer = Customer.builder().name("Emma")
        .rentals(Arrays.asList(movieRental1, movieRental2, movieRental3, movieRental4)).build();

    var result = rentalService.getStatement(customer);

    Assertions.assertEquals(14.5, result.getTotalAmount());
    Assertions.assertEquals(4, result.getBonusPoints());
    Assertions.assertEquals("You've Got Mail", result.getResults().get(0).getMovieTitle());
    Assertions.assertEquals("Matrix", result.getResults().get(1).getMovieTitle());
    Assertions.assertNull(result.getResults().get(2).getMovieTitle());
    Assertions.assertEquals("Fast & Furious X", result.getResults().get(3).getMovieTitle());

    Assertions.assertEquals(0.0, result.getResults().get(2).getAmount());
    Assertions.assertEquals(9, result.getResults().get(3).getAmount());
  }
}
