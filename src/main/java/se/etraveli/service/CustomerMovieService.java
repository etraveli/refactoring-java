package se.etraveli.service;

import se.etraveli.entity.CustomerMovie;

import java.util.List;

public interface CustomerMovieService {
    List<CustomerMovie> createCustomerMovieAll(List<CustomerMovie> customerMovie);
}
