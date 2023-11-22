import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalInfoTest {
    @Test
    public void testMainFunction() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("You've Got Mail", 3.5);
        expected.addRental("Matrix", 2.0);
        expected.addFooter(5.5, 2);

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental("F001", 3));
        movies.add(new MovieRental("F002", 1));

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
        expected.addFooter(5.5, 2);

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental("F002", 3));
        movies.add(new MovieRental("F002", 1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }

    @Test
    public void testAddingInvalidMovieRental() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("You've Got Mail", 3.5);
        expected.addRental("Matrix", 2.0);
        expected.addFooter(5.5, 2);

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental("F001", 3));
        movies.add(new MovieRental("F002", 1));
        movies.add(new MovieRental("F005", 1));

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
        expected.addFooter(0.5, 2);

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental("F001", 3));
        movies.add(new MovieRental("F004", -1));

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
        expected.addFooter(5.5, 2);

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental("F001", 3));
        movies.add(new MovieRental("F002", -1));

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
        expected.addFooter(5.0, 2);

        List<MovieRental> movies = new ArrayList<>();
        movies.add(new MovieRental("F001", 3));
        movies.add(new MovieRental("F003", -1));

        Customer c = new Customer("C. U. Stomer", movies);
        RentalInfo ri = new RentalInfo();

        Statement result = ri.getStatement(c);

        assertEquals(expected, result);
    }
}
