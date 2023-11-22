import entity.Customer;
import entity.Movie;
import entity.MovieRental;
import entity.Statement;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F001.getTitle(), 3.5);
        expected.addRental(Movie.F002.getTitle(), 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.F001, 3));
        movies.add(new MovieRental(Movie.F002, 1));
        Customer customer = new Customer("C. U. Stomer", movies);

        Statement result = new RentalInfo().getStatement(customer);

        if (!result.equals(expected)) {
            throw new AssertionError("Expected: " + System.lineSeparator() + expected + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
        }

        System.out.println("Success");
    }
}
