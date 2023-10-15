package com.rentalmovies.customer.model;

import com.rentalmovies.movierental.model.MovieRental;
import com.rentalmovies.utils.MovieUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 * The Customer class represents a customer entity in a movie rental application.
 * It contains information about the customer, including their ID, name, and movie rentals.
 * This class is also an entity class mapped to a "customers" database table.
 *
 * Cannot make it Immutable because it is JPA limitations
 *
 * @author Sajid Riaz
 */
@Entity
@Table (name = "customers")
public class Customer
{
    @Id
    private String customerId;

    @Column (name = "name", nullable = false)
    private String customerName;

    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MovieRental> movieRentals = new ArrayList<>();

    protected Customer()
    {
        // Required by JPA/Hibernate
    }

    public Customer(String name, String customerId, List<MovieRental> rentals)
    {
        this.customerId = customerId;
        this.customerName = MovieUtils.validateString(name);
        this.movieRentals = new ArrayList<>(Objects.requireNonNull(rentals, "Rentals cannot be null"));
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public List<MovieRental> getMovieRentals()
    {
        return Collections.unmodifiableList(movieRentals);
    }

    @Override
    public String toString()
    {
        return String.format("Customer{name='%s', rentals=%s}", customerName, movieRentals);
    }
}
