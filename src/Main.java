import java.util.List;

public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    Customer testCustomer = getTestCustomer();
    String result = testCustomer.statement();

    if (! result.equals(expected)) {
      throw new AssertionError(
              "Expected: " + System.lineSeparator() + expected + System.lineSeparator() +
                      "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }

  private static Customer getTestCustomer() {
    List<MovieRental> testDataRentals = List.of(
            new MovieRental("F001", 3), new MovieRental("F002", 1));
    return new Customer("C. U. Stomer", testDataRentals);
  }
}