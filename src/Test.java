import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public final class Test {

  private static final MoviesService moviesService = new LocalMoviesService();
  private static final StatementService statementService = new LocalStatementService(moviesService);

  public static void main(String[] args) throws Exception {
    testGetMovieById();
    testCalculateRentPrice();
    testCreateStatement();
    System.out.println("All tests executed successfully");
  }

  private static void testGetMovieById() throws Exception {
    // success
    Movie movie = moviesService.getMovieById("F001");
    if (!"You've Got Mail".equals(movie.title()))
      throw new AssertionError("Expected: You've Got Mail Actual: " + movie.title());
    // invalid id
    try {
      Movie invalidMovie = moviesService.getMovieById("INVALID");
      throw new AssertionError("Expected: thrown error Actual: " + invalidMovie);
    } catch (Exception exception) {
      if (!exception.getMessage().equals("Invalid movie id"))
        throw exception;
    }
    // null id
    try {
      Movie nullMovie = moviesService.getMovieById(null);
      throw new AssertionError("Expected: thrown error Actual: " + nullMovie);
    } catch (Exception exception) {
      if (!exception.getMessage().equals("Invalid movie id"))
        throw exception;
    }
  }

  private static final void testCalculateRentPrice() throws Exception {
    // success
    BigDecimal rentPrice = moviesService.calculateRentPrice(MovieCode.REGULAR, 4);
    if (!rentPrice.equals(new BigDecimal("5.0")))
      throw new AssertionError("Expected: 5.0 Actual: " + rentPrice);
    // invalid days
    try {
      BigDecimal invalidDaysPrice = moviesService.calculateRentPrice(MovieCode.REGULAR, -1);
      throw new AssertionError("Expected: thrown error Actual: " + invalidDaysPrice);
    } catch (Exception exception) {
      if (!exception.getMessage().equals("Invalid days"))
        throw exception;
    }
    // null code
    try {
      BigDecimal invalidCodePrice = moviesService.calculateRentPrice(null, 4);
      throw new AssertionError("Expected: thrown error Actual: " + invalidCodePrice);
    } catch (Exception exception) {
      if (!exception.getMessage().equals("Invalid movie code"))
        throw exception;
    }
  }

  private static void testCreateStatement() throws Exception {
    // null customer name
    try {
      Statement nullCustomertNameStatement = statementService.createStatement(null, new ArrayList<>());
      throw new AssertionError("Expected: thrown error Actual: " + nullCustomertNameStatement);
    } catch (Exception exception) {
      if (!exception.getMessage().equals("Invalid customer name"))
        throw exception;
    }
    // invalid rentals
    try {
      Statement nullMovieRentalsStatement = statementService.createStatement("test",
          Arrays.asList(new MovieRental("Invalid", 3)));
      throw new AssertionError("Expected: thrown error Actual: " + nullMovieRentalsStatement);
    } catch (Exception exception) {
      if (!exception.getMessage().equals("Invalid movie id"))
        throw exception;
    }
    // null rentals
    try {
      Statement nullMovieRentalsStatement = statementService.createStatement("test", null);
      throw new AssertionError("Expected: thrown error Actual: " + nullMovieRentalsStatement);
    } catch (Exception exception) {
      if (!exception.getMessage().equals("Invalid movie rentals"))
        throw exception;
    }
  }

}
