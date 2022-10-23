package movie.rental;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MovieRentalRepo extends ArrayList<MovieRental> {

    public MovieRentalRepo fetch() {
        this.add(new MovieRental("C. U. Stomer", "F001", 3));
        this.add(new MovieRental("C. U. Stomer", "F002", 1));
        return this;
    }

    public ArrayList<MovieRental> filter(String customerName) {
        return new ArrayList<>(this.stream()
                .filter(movieRental -> movieRental.getCustomerName().equals(customerName))
                .collect(Collectors.toList()));
    }
}
