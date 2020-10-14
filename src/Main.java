import java.util.Arrays;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    HashMap<String, Movie> movies = getTestMoviesData();

    Customer c = new Customer("C. U. Stomer", Arrays.asList(movies.get("F001").rentMovie(3), movies.get("F002").rentMovie(1)));

    String result = c.statement();

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }

  private static HashMap<String, Movie> getTestMoviesData() {
    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));
    return movies;
  }
}
