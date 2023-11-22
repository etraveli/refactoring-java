import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Statement expected = new Statement("C. U. Stomer");
    expected.addRental("You've Got Mail", 3.5);
    expected.addRental("Matrix", 2.0);
    expected.incrementFrequentEnterPoints();
    expected.incrementFrequentEnterPoints();

    List<MovieRental> movies = new ArrayList<>();
    movies.add(new MovieRental(Movie.Id.F001, 3));
    movies.add(new MovieRental(Movie.Id.F002, 1));
    Customer customer = new Customer("C. U. Stomer", movies);

    Statement result = new RentalInfo().getStatement(customer);

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + expected + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
