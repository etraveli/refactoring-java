package movie;

import movie.dto.Customer;
import movie.dto.MovieRental;
import movie.service.RentalInfoService;
import movie.service.impl.RentalInfoServiceImpl;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MainTest {

    RentalInfoService rentalInfoService = new RentalInfoServiceImpl();

    @Test
    public void main() {
        String expected = "Rental Record for C. U. Stomer\n\t" +
                "You've Got Mail\t" +
                "3.5\n\t" +
                "Matrix\t" +
                "2.0\n" +
                "Amount owed is 5.5\n" +
                "You earned 2 frequent points\n";

        String result = rentalInfoService.getStatementForCustomer(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        assertThat(result, is(expected));
    }
}