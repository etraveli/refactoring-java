package com.etraveli.movie.rental.service.util;

import com.etraveli.movie.rental.service.configuration.MovieRentalConfiguration;
import com.etraveli.movie.rental.service.dto.Customer;
import com.etraveli.movie.rental.service.dto.Movie;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * This class responsible for create sample data.
 * We can use database to store data as a future enhancement.
 */

@Component
public class SampleData {

    public List<Movie> getMovieData() {
        return List.of(
                new Movie("F001", "You've Got Mail", MovieRentalConfiguration.MovieCode.REGULAR),
                new Movie("F002", "Matrix", MovieRentalConfiguration.MovieCode.REGULAR),
                new Movie("F003", "Cars", MovieRentalConfiguration.MovieCode.CHILDREN),
                new Movie("F004", "Fast & Furious X", MovieRentalConfiguration.MovieCode.NEW));
    }

    public List<Customer> getCustomerData() {
        return List.of(
                new Customer("1", "C. U. Stomer"),
                new Customer("2", "W. Peter"));
    }
}