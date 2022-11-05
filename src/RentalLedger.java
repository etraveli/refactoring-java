import models.Customer;
import models.MovieRental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalLedger {

    Map<String, List<MovieRental>> movieRentalMap = new HashMap<>();

    List<MovieRental> getRentalsForCustomer(Customer customer){
        return movieRentalMap.get(customer.getCustomerId());
    }

    void rentToCustomer(List<MovieRental> movieRentals, Customer customer){
        //TODO validation on movie rental and checking if user already rented the movie
        List<MovieRental> customerMovieRentalList = movieRentalMap.getOrDefault(customer.getCustomerId(), new ArrayList<>());
        customerMovieRentalList.addAll(movieRentals);
        movieRentalMap.put(customer.getCustomerId(), customerMovieRentalList);
    }
}
