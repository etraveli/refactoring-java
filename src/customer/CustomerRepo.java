package customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import movie.rental.MovieRentalRepo;

public class CustomerRepo extends ArrayList<Customer> {

    public CustomerRepo fetch() {
        MovieRentalRepo movieRentalRepo = new MovieRentalRepo().fetch();
        this.add(new Customer("C. U. Stomer", movieRentalRepo.filter("C. U. Stomer")));
        return this;
    }

    public HashMap<String, Customer> indexByName() {
        return this.stream().collect(Collectors.toMap(
                Customer::getName, customer -> customer, (prev, next) -> next, HashMap::new));
    }
}
