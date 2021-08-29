/**
 * Customer class contains information like customer name and movies he/she have rented.
 */

import java.util.List;

public class Customer {
    private String customerName;
    private List<MovieRental> rentedMovies;

    public Customer(String name, List<MovieRental> rentals) {
        customerName = name;
        rentedMovies = rentals;



    }

    public String getName() {
        return customerName;
    }

    public List<MovieRental> getMovies() {
        return rentedMovies;
    }
}
