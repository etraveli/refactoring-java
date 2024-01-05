package com.etraveli.repository;

import com.etraveli.modal.request.CustomerRequest;
import com.etraveli.modal.request.MovieRentalRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CustomerRepositoryOld {

    private final List<CustomerRequest> customerList = new ArrayList<>();

    List<MovieRentalRequest> stomersMovieRentalRequestList = List.of(
            /*new MovieRentalRequest("F001", 3),
            new MovieRentalRequest("F002", 1)*/
    );

    List<MovieRentalRequest> sahansMovieRentalRequestList = List.of(
          /*  new MovieRentalRequest("F001", 3),
            new MovieRentalRequest("F002", 1)*/
    );

    public CustomerRepositoryOld() {
//        customerList.add(new CustomerRequest(1L, "C. U. Stomer", stomersMovieRentalList));
//        customerList.add(new CustomerRequest(2L, "D. Sahan Ekanayake", sahansMovieRentalList));
    }

    public CustomerRequest getCustomerByCustomerId(Long customerId) {
        return customerList.stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }
}
