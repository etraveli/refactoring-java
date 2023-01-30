import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    //introducing variables to keep them consistent across the 2 strings.
    String customerName = "C. U. Stomer";
    MovieRental movie1 = new MovieRental("F001", 3);
    MovieRental movie2 = new MovieRental("F002", 1);
    String expected = "Rental Record for " + customerName +
            "\n\t" + "You've Got Mail\t3.5" +
            "\n\tMatrix\t2.0" +
            "\nAmount owed is 5.5" +
            "\nYou earned 2 frequent points\n";
    
    String result = new RentalInfo().statement(new Customer(customerName, Arrays.asList(movie1, movie2)));

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }
    System.out.println(result);
    System.out.println("Success");
  }
}
