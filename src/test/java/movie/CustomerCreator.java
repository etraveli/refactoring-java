package movie;

import movie.dto.Customer;
import movie.dto.MovieRental;

import java.util.Arrays;
import java.util.List;

public class CustomerCreator {

    public static Customer createCustomer() {
        return new Customer("C. U. Stomer",
                Arrays.asList(
                        new MovieRental("F001", 3),
                        new MovieRental("F002", 1)));
    }

    public static Customer createCustomer(String name, List<MovieRental> movieRentals) {
        return new Customer(name, movieRentals);
    }
}
