import java.util.Collections;
import java.util.List;

public class Customer {
    private String name;
    private List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
        // It's good to always have safe / not-null lists
        if (this.rentals == null){
            this.rentals = Collections.emptyList();
        }
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
}
