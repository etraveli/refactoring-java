package refactoring.java.model;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerTest {

    @Test
    void getName() {
        Customer customer = new Customer("Arne Anka", null);
        assertEquals("Arne Anka", customer.getName());
    }

    @Test
    void getRentals() {
        List<MovieRental> rentals = new ArrayList<>();
        rentals.add(new MovieRental("F001", 1));
        rentals.add(new MovieRental("F002", 2));

        Customer customer = new Customer(null, rentals);

        assertNotNull(customer.getRentals());
        assertEquals(2, customer.getRentals().size());
        assertEquals("F001", customer.getRentals().get(0).getMovieId());
        assertEquals("F002", customer.getRentals().get(1).getMovieId());
    }
}