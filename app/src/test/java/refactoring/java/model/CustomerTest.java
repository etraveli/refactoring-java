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
        assertEquals(customer.getName(), "Arne Anka");
    }

    @Test
    void getRentals() {
        List<MovieRental> rentals = new ArrayList<>();
        rentals.add(new MovieRental("F001", 1));
        rentals.add(new MovieRental("F002", 2));

        Customer customer = new Customer(null, rentals);

        assertNotNull(customer.getRentals());
        assertEquals(customer.getRentals().size(), 2);
        assertEquals(customer.getRentals().get(0).getMovieId(), "F001");
        assertEquals(customer.getRentals().get(1).getMovieId(), "F002");
    }
}