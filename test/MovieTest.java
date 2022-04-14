import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    static final Movie REGULAR_MOVIE = new RegularMovie("title");
    static final Movie NEW_MOVIE = new NewMovie("title");
    static final Movie CHILD_MOVIE = new ChildrensMovie("title");

    @Test
    public void for_regular_movies_amount_is_two() {
        assertEquals(2.0, REGULAR_MOVIE.getRentalAmount(1));
    }

    @Test
    public void for_regular_movies_older_than_two_days_amount_is_increased() {
        assertEquals(3.5, REGULAR_MOVIE.getRentalAmount(3));
    }

    @Test
    public void for_new_movies_amount_is_three_times_days() {
        assertEquals(6.0, NEW_MOVIE.getRentalAmount(2));
    }

    @Test
    public void for_child_movies_amount_is_one_point_five() {
        assertEquals(1.5, CHILD_MOVIE.getRentalAmount(1));
    }

    @Test
    public void for_child_movies_older_than_three_days_amount_is_increased() {
        assertEquals(3.0, CHILD_MOVIE.getRentalAmount(4));
    }

    @Test
    public void for_normal_movies_frequency_points_is_one() {
        assertEquals(1, REGULAR_MOVIE.getFrequencyPoints(1));
    }

    @Test
    public void for_new_movies_that_are_rented_for_more_than_two_days_points_is_two() {
        assertEquals(2, NEW_MOVIE.getFrequencyPoints(3));
    }
}
