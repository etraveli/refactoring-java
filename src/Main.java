import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    //data setup that can be stored data
    Customer customer = new Customer(1, "C. U. Stomer");

    MovieType regularMovieType = new MovieType(1, "regular", 2, 2, 1.5, 0, 0);
    MovieType childrensMovieType = new MovieType(2, "childrens", 1.5, 3, 1.5, 0, 0);
    MovieType newMovieType = new MovieType(3, "new", 3, 0, 1.5, 2, 1);

    Movie movie1 = new Movie(1, "F001", "You've Got Mail", regularMovieType);
    Movie movie2= new Movie(2, "F002", "Matrix", regularMovieType);
    Movie movie3 = new Movie(3, "F003", "Cars", childrensMovieType);
    Movie movie4 = new Movie(4, "F004", "Fast & Furious X", newMovieType);

    //movie rental sample
    List<MovieRental> movieRentalList = new ArrayList<>();
    movieRentalList.add(new MovieRental(movie1,3));
    movieRentalList.add(new MovieRental(movie2,1));

    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    String result =new RentalInfo().statement(customer,movieRentalList);

    //add more test cases
    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
