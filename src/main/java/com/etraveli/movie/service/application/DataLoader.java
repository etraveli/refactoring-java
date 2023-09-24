package com.etraveli.movie.service.application;

import com.etraveli.movie.service.dto.Customer;
import com.etraveli.movie.service.dto.Movie;
import com.etraveli.movie.service.dto.MovieType;
import com.etraveli.movie.service.dto.RentalFee;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataLoader {

    private final DataHolder dataHolder;

    @PostConstruct
    public void loadRentalFeeData() {
        // define rental fees
        var childrenMovieRentalFee = RentalFee.builder()
                .flatFee(BigDecimal.valueOf(1.5))
                .flatFeeLimit(3)
                .dailyFee(BigDecimal.valueOf(1.5))
                .build();

        var regularMovieRentalFee = RentalFee.builder()
                .flatFee(BigDecimal.valueOf(2))
                .flatFeeLimit(2)
                .dailyFee(BigDecimal.valueOf(1.5))
                .build();

        var newMovieRentalFee = RentalFee.builder()
                .flatFee(BigDecimal.ZERO)
                .flatFeeLimit(0)
                .dailyFee(BigDecimal.valueOf(3))
                .build();

        // hardcoded movie details
        List<Movie> movies = Arrays.asList(
                new Movie("F001", "You've Got Mail", MovieType.REGULAR),
                new Movie("F002", "Matrix", MovieType.REGULAR),
                new Movie("F003", "Cars", MovieType.CHILDREN),
                new Movie("F004", "Fast & Furious X", MovieType.NEW)
        );

        // hardcoded customer details
        List<Customer> customers = Arrays.asList(
                new Customer("001", "C. U. Stomer")
        );

        //set rentalFee in dataHolder
        dataHolder.getRentalFeeStructure().put(MovieType.CHILDREN, childrenMovieRentalFee);
        dataHolder.getRentalFeeStructure().put(MovieType.REGULAR, regularMovieRentalFee);
        dataHolder.getRentalFeeStructure().put(MovieType.NEW, newMovieRentalFee);

        //set movie and customer data in dataHolder
        movies.forEach(movie -> dataHolder.getMovies().add(movie));
        customers.forEach(customer -> dataHolder.getCustomers().add(customer));
    }
}
