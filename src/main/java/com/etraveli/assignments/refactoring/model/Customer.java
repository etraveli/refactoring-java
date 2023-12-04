package com.etraveli.assignments.refactoring.model;

import java.util.List;

public record Customer(String name, List<MovieRental> rentals) {}
