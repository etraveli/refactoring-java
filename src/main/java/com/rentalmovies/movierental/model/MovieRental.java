package com.rentalmovies.movierental.model;

import com.rentalmovies.customer.model.Customer;
import com.rentalmovies.movie.model.Movie;
import com.rentalmovies.utils.MovieUtils;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.*;

/**
 * The MovieRental entity represents a movie rental transaction within the application.
 * It contains information about the rental, such as the movie ID, rental days, 
 * associated customer, and more. This class is also an entity class mapped 
 * to the "movie_rentals" database table.
 *
 * Cannot make it Immutable because it is JPA limitations
 *
 * @author Sajid riaz
 */
@Entity
@Table (name = "movie_rentals")
public class MovieRental
{
    @Id
    private Long id;

    @NotNull
    @Column (nullable = false)
    private String movieId;

    @Column (nullable = false)
    private int rentalDays;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "customer_id")
    private Customer customer;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "movie_id")
    private Movie movie;

    protected MovieRental()
    {
        // JPA requires a no-arg constructor
    }

    public MovieRental(Long id, String movieId, int days)
    {
        this(movieId, days);
        this.id = id;
    }

    public MovieRental(String movieId, int days)
    {
        this.movieId = MovieUtils.validateString(movieId);
        this.rentalDays = MovieUtils.validateRentalDays(days);
    }

    public Long getId()
    {
        return id;
    }

    public String getMovieId()
    {
        return movieId;
    }

    public int getRentalDays()
    {
        return rentalDays;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public Movie getMovie()
    {
        return movie;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }

    @Override
    public String toString()
    {
        return String.format("MovieRental{id=%d, movieId='%s', rentalDays=%d}", id, movieId, rentalDays);
    }
}
