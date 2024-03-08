package com.mithwick93.refactoring.java.entity;

import java.util.List;

/**
 * Customer class to represent a customer with a name and a list of rentals
 *
 * @param name    of the customer
 * @param rentals List of current rentals the customer has
 */
public record Customer(String name, List<MovieRental> rentals) {
}
