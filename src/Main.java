import main.java.movie.InMemoryMovieRepository;
import main.java.movie.MovieRental;
import main.java.rental.RentalCalculator;
import main.java.rental.RentalStatementGenerator;
import main.java.customer.Customer;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    InMemoryMovieRepository movieRepository = new InMemoryMovieRepository();
    RentalCalculator rentalCalculator = new RentalCalculator();
    RentalStatementGenerator statementGenerator = new RentalStatementGenerator(movieRepository, rentalCalculator);

    String result = statementGenerator
        .generateStatement(
            new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

    System.out.println(result);
  }
}