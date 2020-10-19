import java.util.List;

public class Main {

  public static void main(String[] args) {
    String expected =
            "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

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
    List<Rental> testRentals = List.of(
            new Rental(new Movie("F001", "You've Got Mail", "regular"), 3),
            new Rental(new Movie("F002","Matrix", "regular"), 1));
    return new Customer("C. U. Stomer", testRentals);
  }
}