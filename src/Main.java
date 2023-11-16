import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    Customer customer = new Customer("C. U. Stomer",
        Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1)));

    Statement statement = new Statement(customer);

    if (!statement.toString().equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator()
          + System.lineSeparator() + "Got: " + System.lineSeparator() + statement.toString());
    }

    System.out.println(statement);
  }
}
