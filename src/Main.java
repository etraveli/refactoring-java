import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    final String expected = "Rental Record for C. U. Stomer (ID: 1)" + System.lineSeparator() + "\tYou've Got Mail\t3.5"
        + System.lineSeparator() + "\tMatrix\t2.0" + System.lineSeparator() + "Amount owed is 5.5"
        + System.lineSeparator() + "You earned 2 frequent points" + System.lineSeparator();

    // Creating the movies
    Movie m1 = new Movie("You've Got Mail", Movie.MovieTag.REGULAR);
    Movie m2 = new Movie("Matrix", Movie.MovieTag.REGULAR);
    // The following 2 are not used but they were kepts as part of the original code
    Movie m3 = new Movie("Cars", Movie.MovieTag.CHILDREN);
    Movie m4 = new Movie("Fast & Furious X", Movie.MovieTag.NEW);

    // Creating the movie rentals
    MovieRental mr1 = new MovieRental(m1, 3);
    MovieRental mr2 = new MovieRental(m2, 1);

    // Creating the Customer
    Customer c1 = new Customer("C. U. Stomer", Arrays.asList(mr1, mr2));

    // Getting the rental information
    String result = new RentalInfo().getCustomerStatement(c1);

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator()
          + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}