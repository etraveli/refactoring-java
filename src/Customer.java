import java.util.List;

/**
 * We assume, we don't save customers in our database.
 * When a random customer rents movie(s) from our database, we just print an information slip.
 * If we would save customers, it would be better to add customerId to this entity. (Future prospect) 
**/
public class Customer {
	
    private String name;
    private List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
}
