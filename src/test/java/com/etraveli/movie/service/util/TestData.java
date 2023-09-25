package com.etraveli.movie.service.util;

import com.etraveli.movie.service.dto.Customer;
import com.etraveli.movie.service.dto.Movie;
import com.etraveli.movie.service.dto.MovieType;
import com.etraveli.movie.service.dto.RentOrder;
import com.etraveli.movie.service.dto.RentOrderLine;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static List<Movie> getMovieList() {
        return Arrays.asList(
                new Movie("F001", "You've Got Mail", MovieType.REGULAR),
                new Movie("F002", "Matrix", MovieType.REGULAR),
                new Movie("F003", "Cars", MovieType.CHILDREN),
                new Movie("F004", "Fast & Furious X", MovieType.NEW)
        );
    }

    public static List<Customer> getCustomerList() {
        return Arrays.asList(
                Customer.builder()
                        .customerID("001")
                        .customerName("Customer 1")
                        .build(),
                Customer.builder()
                        .customerID("002")
                        .customerName("Customer 2")
                        .build()
        );
    }

    public static RentOrder getRentOrder(String customerID) {
        return RentOrder.builder()
                .customerID(customerID)
                .orderLineList(Arrays.asList(
                        RentOrderLine.builder()
                                .movieCode("F001")
                                .rentDays(2)
                                .build(),
                        RentOrderLine.builder()
                                .movieCode("F002")
                                .rentDays(3)
                                .build()))
                .build();
    }
}
