import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import main.java.customer.Customer;
import main.java.movie.InMemoryMovieRepository;
import main.java.movie.MovieRental;
import main.java.rental.RentalCalculator;
import main.java.rental.RentalStatementGenerator;

public class MainTest {

    @Test

    public void testGenerateStatement() {
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        InMemoryMovieRepository movieRepository = new InMemoryMovieRepository();
        RentalCalculator rentalCalculator = new RentalCalculator();
        RentalStatementGenerator statementGenerator = new RentalStatementGenerator(movieRepository, rentalCalculator);

        String result = statementGenerator
                .generateStatement(
                        new Customer("C. U. Stomer",
                                Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        assertEquals(expected, result);
    }

    /*
     * Tests the scenario where the customer has no rentals.
     * Ensures the statement correctly reflects zero owed amount and zero frequent
     * points.
     */
    @Test

    public void testGenerateStatementWithNoRentals() {
        String expected = "Rental Record for C. U. Stomer\nAmount owed is 0.0\nYou earned 0 frequent points\n";

        InMemoryMovieRepository movieRepository = new InMemoryMovieRepository();
        RentalCalculator rentalCalculator = new RentalCalculator();
        RentalStatementGenerator statementGenerator = new RentalStatementGenerator(movieRepository, rentalCalculator);

        String result = statementGenerator.generateStatement(new Customer("C. U. Stomer", Collections.emptyList()));

        assertEquals(expected, result);
    }

    /*
     * Tests the scenario with multiple rentals.
     * Ensures the statement correctly calculates the total amount owed and frequent
     * points.
     */

    @Test
    public void testGenerateStatementWithMultipleRentals() {
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        InMemoryMovieRepository movieRepository = new InMemoryMovieRepository();
        RentalCalculator rentalCalculator = new RentalCalculator();
        RentalStatementGenerator statementGenerator = new RentalStatementGenerator(movieRepository, rentalCalculator);

        String result = statementGenerator.generateStatement(
                new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        assertEquals(expected, result);
    }

    /*
     * Tests the scenario with different rental periods.
     * Ensures the statement correctly calculates the amount owed for different
     * rental durations.
     */
    @Test
    public void testGenerateStatementWithDifferentRentalPeriods() {
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t6.5\nAmount owed is 10.0\nYou earned 2 frequent points\n";

        InMemoryMovieRepository movieRepository = new InMemoryMovieRepository();
        RentalCalculator rentalCalculator = new RentalCalculator();
        RentalStatementGenerator statementGenerator = new RentalStatementGenerator(movieRepository, rentalCalculator);

        String result = statementGenerator.generateStatement(
                new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 5))));

        assertEquals(expected, result);
    }

}