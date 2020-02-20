import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {

    /*      C H A N G E S
     * Defined constants instead of repetitive bug prone literal string values (like movie types)
     * Exposed some properties of RentalInfo to outside to be able to set & override and
     * have a testable object and function
     * Wrote some test with custom (or mocked) values and functions
     * Changed or added or completely refactored some parts of the code to be safe against Null values
     */

  public static void main(String[] args) {
    testOld();
      testWithCustomAssert();
      testCustomMovies();
      testCustomMoviesAndAmountCalculators();
      testCustomMoviesAndAmountCalculatorsAndUnknownMovie();
      testCustomBonusCalculator();
  }

    /**
     * The original existing test
     */
  static void testOld(){
      String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

      String result = new RentalInfo().statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

      if (!result.equals(expected)) {
          throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
      }

      System.out.println("Success");
  }


    /**
     * No change in test, just using the assertion method
     */
  static void testWithCustomAssert(){
      String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

      String result = new RentalInfo().statement(
              new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

      assertEquals("testWithCustomAssert",expected, result);
  }

    /**
     * Set custom movies to be used by statement since the test has no knowledge about the real
     * content of movies DB
     */
    static void testCustomMovies(){
        HashMap<String, Movie> movies = new HashMap<String, Movie>();
        movies.put("MOV-1", new Movie("Batman", RentalInfo.REGULAR));

        Customer customer = new Customer("Bob",
                Arrays.asList(new MovieRental("MOV-1", 3)));

        String expected = "Rental Record for Bob\n\tBatman\t3.5\nAmount owed is 3.5\nYou earned 1 frequent points\n";

        String result = new RentalInfo().setMovies(movies).statement(customer);

        assertEquals("testCustomMovies",expected, result);
    }

    /**
     * Set movies and amount calculators to be used by statement since the test has no knowledge about the real
     * content of movies DB and calculators logic
     */
    static void testCustomMoviesAndAmountCalculators(){
        Map<String, Movie> movies = new HashMap<String, Movie>();
        movies.put("MOV-1", new Movie("Batman", RentalInfo.REGULAR));

        Map<String, Function<Integer, Double>> calculators = new HashMap<String, Function<Integer, Double>>();
        calculators.put(RentalInfo.REGULAR, (days) -> 1.0);

        Customer customer = new Customer("Bob",
                Arrays.asList(new MovieRental("MOV-1", 3000)));

        String expected = "Rental Record for Bob\n\tBatman\t1.0\nAmount owed is 1.0\nYou earned 1 frequent points\n";

        String result = new RentalInfo()
                .setMovies(movies).setCalculators(calculators).statement(customer);

        assertEquals("testCustomMoviesAndAmountCalculators",expected, result);
    }

    /**
     * Set movies and amount calculators to be used by statement since the test has no knowledge about the real
     * content of movies DB and calculators logic, also test with an unknown movie
     */
    static void testCustomMoviesAndAmountCalculatorsAndUnknownMovie(){
        Map<String, Movie> movies = new HashMap<String, Movie>();
        movies.put("MOV-1", new Movie("Batman", RentalInfo.REGULAR));

        Map<String, Function<Integer, Double>> calculators = new HashMap<String, Function<Integer, Double>>();
        calculators.put(RentalInfo.REGULAR, (days) -> 1500.0);
        calculators.put(RentalInfo.NEW, (days) -> 5.0);


        Customer customer = new Customer("Bob",
                Arrays.asList(new MovieRental("F001", 3000)));

        String result = new RentalInfo()
                .setMovies(movies).setCalculators(calculators).statement(customer);

        boolean containsMovieID = result.contains("F001");
        boolean calculatedAsRegular = result.contains("1500.0");
        if (containsMovieID && calculatedAsRegular){
            System.out.println("testCustomMoviesAndAmountCalculatorsAndUnknownMovie: Passed");
        }else {
            throw new AssertionError("testCustomMoviesAndAmountCalculatorsAndUnknownMovie: Failed " + System.lineSeparator()
                    + "Got: " + System.lineSeparator() + result);
        }
    }

    /**
     * Set a custom bonus calculator
     */
    static void testCustomBonusCalculator(){
        HashMap<String, Movie> movies = new HashMap<String, Movie>();
        movies.put("MOV-1", new Movie("Batman", RentalInfo.REGULAR));

        BiFunction<String,Integer,Integer> bonusCalculator = (movieType, days) -> 1;

        Customer customer = new Customer("Bob",
        Arrays.asList(new MovieRental("MOV-1", 3)));

        String expected = "Rental Record for Bob\n\tBatman\t3.5\nAmount owed is 3.5\nYou earned 2 frequent points\n";

        String result = new RentalInfo().setMovies(movies).setBonusCalculator(bonusCalculator).statement(customer);

        assertEquals("testCustomBonusCalculator",expected, result);
    }

  static void assertEquals(String testName, String expected, String actual){
      if (!Objects.equals(expected, actual)) {
          throw new AssertionError(testName + ": Failed " + System.lineSeparator()
                  + "Expected: " + System.lineSeparator() + String.format(expected)
                          + System.lineSeparator() + System.lineSeparator() + "Got: "
                          + System.lineSeparator() + actual);
      }
      System.out.println(testName + ": Passed");
  }

}
