import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RentalInfoServiceTest {

    RentalInfoService rentalService = RentalInfoService.withMovies(
            Map.of(
                "REGULAR_MOVIE", new RegularMovie("Random movie"),
                "CHILDRENS_MOVIE", new ChildrensMovie("Random kids movie"),
                "NEW_MOVIE", new NewMovie("Brand new random movie")
            )
    );

    @Test
    public void the_name_of_the_customer_is_printed() {
        var customerWithName = new Customer("A name", List.of());

        String result = rentalService.getCustomerStatus(customerWithName);

        assertTrue(result.startsWith("Rental Record for A name\n"));
    }

    @Test
    public void if_customer_has_null_rentals_then_amount_and_points_are_zero() {
        var customer = new Customer(null, null);

        String result = rentalService.getCustomerStatus(customer);

        assertEquals("Rental Record for null\nAmount owed is 0.0\nYou earned 0 frequent points\n", result);
    }

    @Test
    public void if_customer_has_empty_rentals_then_amount_and_points_are_zero() {
        var customerWithNoRentals = new Customer(null, List.of());

        String result = rentalService.getCustomerStatus(customerWithNoRentals);

        assertEquals("Rental Record for null\nAmount owed is 0.0\nYou earned 0 frequent points\n", result);
    }

    @Test
    public void points_are_incremented_with_each_rental() {
        var customerWithTwoRentals = customerWith(
                new MovieRental("REGULAR_MOVIE", 1),
                new MovieRental("REGULAR_MOVIE", 1));

        String result = rentalService.getCustomerStatus(customerWithTwoRentals);

        assertTrue(result.contains("You earned 2 frequent points"));
    }

    @Test
    public void unknown_movie_is_ignored() {
        var customerWithOnlyKnownMovie = customerWith(
                new MovieRental("REGULAR_MOVIE", 1));
        var customerWithUnknownMovie = customerWith(
                new MovieRental("UNKNOWN_MOVIE", 1),
                new MovieRental("REGULAR_MOVIE", 1));

        assertEquals(rentalService.getCustomerStatus(customerWithOnlyKnownMovie),
                rentalService.getCustomerStatus(customerWithUnknownMovie));
    }

    @Test
    public void can_calculate_several_amounts_at_once() {
        var customerWithSeveralRentals = customerWith(
                new MovieRental("NEW_MOVIE", 2),
                new MovieRental("CHILDRENS_MOVIE", 4));

        String result = rentalService.getCustomerStatus(customerWithSeveralRentals);

        assertTrue(result.contains("Brand new random movie\t6.0\n\tRandom kids movie\t3.0"));
    }

    @Test
    public void total_amount_is_the_sum_of_all_rental_amounts() {
        var customer = customerWith(
                new MovieRental("REGULAR_MOVIE", 1),
                new MovieRental("REGULAR_MOVIE", 3));

        String result = rentalService.getCustomerStatus(customer);

        assertTrue(result.contains("Amount owed is 5.5"));
    }

    private Customer customerWith(MovieRental... rentals) {
        return new Customer(null, Arrays.asList(rentals));
    }
}