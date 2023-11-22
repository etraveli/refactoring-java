import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    Statement expected = new Statement("C. U. Stomer");
    expected.addRental("You've Got Mail", 3.5);
    expected.addRental("Matrix", 2.0);
    expected.addFooter(5.5, 2);


    Statement result = new RentalInfo().getStatement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + expected + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
