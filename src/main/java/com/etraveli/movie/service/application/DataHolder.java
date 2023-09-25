package com.etraveli.movie.service.application;

import com.etraveli.movie.service.dto.Customer;
import com.etraveli.movie.service.dto.Movie;
import com.etraveli.movie.service.dto.MovieType;
import com.etraveli.movie.service.dto.RentalFee;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * This class is for holding MetaData, customer and movie sample data
 * This class can be removed after providing proper datasource
 */
@Component
public class DataHolder {

    private static final Map<MovieType, RentalFee> rentalFee = new EnumMap<>(MovieType.class);
    private final List<Movie> movieList = new ArrayList<>();
    private final List<Customer> customerList = new ArrayList<>();

    public Map<MovieType, RentalFee> getRentalFeeStructure() {
        return rentalFee;
    }

    public List<Movie> getMovies() {
        return movieList;
    }

    public List<Customer> getCustomers() {
        return customerList;
    }

    public RentalFee getRentalFeeByCode(MovieType type) {
        return rentalFee.get(type);
    }
}
