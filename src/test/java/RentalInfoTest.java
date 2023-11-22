import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RentalInfoTest {
    @Test
    public void testMainFunction() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("You've Got Mail", 3.5);
        expected.addRental("Matrix", 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.Id.F001, 3));
        movies.add(new MovieRental(Movie.Id.F002, 1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void testAddingTheSameMovie() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("Matrix", 3.5);
        expected.addRental("Matrix", 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.Id.F002, 3));
        movies.add(new MovieRental(Movie.Id.F002, 1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void testNegativeRentingDaysForANewCodeMove() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("You've Got Mail", 3.5);
        expected.addRental("Fast & Furious X", -3.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.Id.F001, 3));
        movies.add(new MovieRental(Movie.Id.F004, -1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void testNegativeRentingDaysForARegularCodeMove() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("You've Got Mail", 3.5);
        expected.addRental("Matrix", 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.Id.F001, 3));
        movies.add(new MovieRental(Movie.Id.F002, -1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void testNegativeRentingDaysForAChildrensCodeMove() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("You've Got Mail", 3.5);
        expected.addRental("Cars", 1.5);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental(Movie.Id.F001, 3));
        movies.add(new MovieRental(Movie.Id.F003, -1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void testToMakeSureAllMovieIdsAreAddedToStock() {
        Arrays.asList(Movie.Id.values())
                .forEach(key -> {
                    assertTrue(RentalInfo.getMovieStock().containsKey(key), "Missing movie stock entry for Id: " + key);
                });
    }
}
