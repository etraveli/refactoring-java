package com.rentalmovies.models;

/**
 * Making a class immutable is beneficial.
 * 1. Thread Safety: Immutable objects are inherently thread-safe
 * 2. It eliminates the need for synchronization, enhancing performance in multi-threaded environments
 * 3. There’s less risk of unintended side-effects and state changes, reducing bugs and errors.
 * 4. Immutable objects can be safely shared and reused across different parts of a program without cloning or copying.
 * 5. Since their state doesn’t change, their hashcodes can be cached, speeding up operations in hash-based collections.
 *
 */

import com.rentalmovies.utils.MovieUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// Make class immutable
public final class Customer
{
    private final String customerName;
    private final List<MovieRental> movieRentals;

    public Customer(String name, List<MovieRental> rentals)
    {
        // Data passed to the constructor is validated to ensure that the object is always in a valid state.
        this.customerName = MovieUtils.validateString(name);
        this.movieRentals = new ArrayList<>(Objects.requireNonNull(rentals, "Rentals cannot be null")); // keep original list unchanged
        //Deep copy ensures that the internal state of the object is not changed by external references.
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public List<MovieRental> getMovieRentals()
    {
        return Collections.unmodifiableList(movieRentals); // Keep internal list private to make sure that caller can,t change the internal state of the object
    }

    @Override
    public String toString()
    {
        return String.format("Customer{name='%s', rentals=%s}", customerName, movieRentals);
    }
}
