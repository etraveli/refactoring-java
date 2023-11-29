package com.etraveli.assignments.refactoring.models;

import java.util.List;

public record Customer(String name, List<MovieRental> rentals) {}
