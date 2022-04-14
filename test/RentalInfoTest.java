import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RentalInfoTest {

    static final String REGULAR_MOVIE_ID = "F001";
    static final String CHILDRENS_MOVIE_ID = "F003";
    static final String NEW_MOVIE_ID = "F004";
    static final String UNKNOWN_MOVIE_ID = "F005";

    RentalInfo rentalInfo = new RentalInfo();

    @Test
    public void the_name_of_the_customer_is_printed() {
        var customerWithName = new Customer("A name", List.of());

        String result = rentalInfo.statement(customerWithName);

        assertTrue(result.startsWith("Rental Record for A name\n"));
    }

    @Test
    public void throws_exception_when_customer_has_null_rentals() {
        //TODO Ideally the code should be null safe. This should be fixed later.
        var customer = new Customer(null, null);

        try {
            rentalInfo.statement(customer);
            fail();
        } catch (NullPointerException npe) {
            return;
        }
        fail();
    }

    @Test
    public void if_customer_has_no_rentals_then_amount_and_points_are_zero() {
        var customerWithNoRentals = new Customer(null, List.of());

        String result = rentalInfo.statement(customerWithNoRentals);

        assertEquals("Rental Record for null\nAmount owed is 0.0\nYou earned 0 frequent points\n", result);
    }

    @Test
    public void points_are_incremented_with_each_rental() {
        var customerWithTwoRentals = customerWith(
                new MovieRental(REGULAR_MOVIE_ID, 1),
                new MovieRental(REGULAR_MOVIE_ID, 1));

        String result = rentalInfo.statement(customerWithTwoRentals);

        assertTrue(result.contains("You earned 2 frequent points"));
    }

    @Test
    public void points_are_incremented_when_new_movies_are_rented_for_more_than_two_days() {
        var customerWithNewMovie = customerWith(new MovieRental(NEW_MOVIE_ID, 3));

        String result = rentalInfo.statement(customerWithNewMovie);

        assertTrue(result.contains("You earned 2 frequent points"));
    }

    @Test
    public void throws_exception_for_unknown_movie() {
        //TODO Ideally the code should be null safe. This should be fixed later.
        var customer = customerWith(new MovieRental(UNKNOWN_MOVIE_ID, 1));

        try {
            rentalInfo.statement(customer);
            fail();
        } catch (NullPointerException npe) {
            return;
        }
        fail();
    }

    @Test
    public void for_regular_movies_amount_is_two() {
        String result = rentalInfo.statement(customerWith(new MovieRental(REGULAR_MOVIE_ID, 1)));
        assertTrue(result.contains("You've Got Mail\t2.0"));
    }

    @Test
    public void for_regular_movies_older_than_two_days_amount_is_increased() {
        String result = rentalInfo.statement(customerWith(new MovieRental(REGULAR_MOVIE_ID, 3)));
        assertTrue(result.contains("You've Got Mail\t3.5"));
    }

    @Test
    public void for_new_movies_amount_is_three_times_days() {
        String result = rentalInfo.statement(customerWith(new MovieRental(NEW_MOVIE_ID, 2)));
        assertTrue(result.contains("Fast & Furious X\t6.0"));
    }

    @Test
    public void for_child_movies_amount_is_one_point_five() {
        String result = rentalInfo.statement(customerWith(new MovieRental(CHILDRENS_MOVIE_ID, 1)));
        assertTrue(result.contains("Cars\t1.5"));
    }

    @Test
    public void for_child_movies_older_than_three_days_amount_is_increased() {
        String result = rentalInfo.statement(customerWith(new MovieRental(CHILDRENS_MOVIE_ID, 4)));
        assertTrue(result.contains("Cars\t3.0"));
    }

    @Test
    public void can_calculate_several_amounts_at_once() {
        var customerWithSeveralRentals = customerWith(
                new MovieRental(NEW_MOVIE_ID, 2),
                new MovieRental(CHILDRENS_MOVIE_ID, 4));

        String result = rentalInfo.statement(customerWithSeveralRentals);

        assertTrue(result.contains("Fast & Furious X\t6.0\n\tCars\t3.0"));
    }

    @Test
    public void total_amount_is_the_sum_of_all_rental_amounts() {
        var customer = customerWith(
                new MovieRental(REGULAR_MOVIE_ID, 1),
                new MovieRental(REGULAR_MOVIE_ID, 3));

        String result = rentalInfo.statement(customer);

        assertTrue(result.contains("Amount owed is 5.5"));
    }

    private Customer customerWith(MovieRental... rentals) {
        return new Customer(null, Arrays.asList(rentals));
    }
}