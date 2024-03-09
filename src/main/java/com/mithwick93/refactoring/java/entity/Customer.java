package com.mithwick93.refactoring.java.entity;

import java.util.List;

/**
 * Customer class to represent a customer with a name and a list of rentals.
 *
 * @param name    name of the customer
 * @param rentals list of current rentals the customer has
 */
public record Customer(String name, List<MovieRental> rentals) {
}
