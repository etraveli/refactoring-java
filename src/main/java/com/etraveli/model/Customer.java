package com.etraveli.model;

import java.util.List;

/**
 * @param name    of the customer
 * @param rentals List of current rentals the customer has
 */
public record Customer(String name, List<MovieRental> rentals) {
}
