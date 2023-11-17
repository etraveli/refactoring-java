import java.util.Arrays;
import java.util.List;

public final class Main {

  private static final MoviesService moviesService = new MockMoviesService();
  private static final StatementService statementService = new MockStatementService(moviesService);

  public static void main(String[] args) throws Exception {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
    String customerName = "C. U. Stomer";
    List<MovieRental> movieRentals = Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1));
    Statement statement = statementService.createStatement(customerName, movieRentals);
    if (!expected.equals(statement.toString())) {
      throw new AssertionError(System.lineSeparator() + "Expected: " + System.lineSeparator() + String.format(expected)
          + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + statement);
    }
    System.out.println("Success");
  }

}
