import entity.Customer;
import entity.Movie;
import entity.MovieRental;
import entity.Statement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RentalInfoTest {
    @Test
    public void givenValidCustomer_whenGetStatement_thenAssertEqualsExpected() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F001.getTitle(), 3.5);
        expected.addRental(Movie.F002.getTitle(), 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.F001, 3));
        movies.add(new MovieRental(Movie.F002, 1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void givenRentalSameMovie_whenGetStatement_thenAssertEqualsExpected() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F002.getTitle(), 3.5);
        expected.addRental(Movie.F002.getTitle(), 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.F002, 3));
        movies.add(new MovieRental(Movie.F002, 1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void givenRentingNewCategoryWithNegativeDays_whenGetStatement_thenAssertEqualsExpected() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F001.getTitle(), 3.5);
        expected.addRental(Movie.F004.getTitle(), -3.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.F001, 3));
        movies.add(new MovieRental(Movie.F004, -1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void givenRentingRegularCategoryWithNegativeDays_whenGetStatement_thenAssertEqualsExpected() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F001.getTitle(), 3.5);
        expected.addRental(Movie.F002.getTitle(), 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.F001, 3));
        movies.add(new MovieRental(Movie.F002, -1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void givenRentingChildrenCategoryWithNegativeDays_whenGetStatement_thenAssertEqualsExpected() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F001.getTitle(), 3.5);
        expected.addRental(Movie.F003.getTitle(), 1.5);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.F001, 3));
        movies.add(new MovieRental(Movie.F003, -1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }
}
