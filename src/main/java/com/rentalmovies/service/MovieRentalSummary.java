package com.rentalmovies.service;

import com.rentalmovies.movie.model.MovieResponseDTO;

/**
 * The MovieRentalSummary class encapsulates information about a specific 
 * movie rental, including the customer's name, the movie details, the 
 * rental amount, and the frequent renter points earned from the rental. 
 * This class is immutable, ensuring that once a MovieRentalSummary 
 * object is created, its state cannot be altered.
 *
 * @author Sajid Riaz
 */
public class MovieRentalSummary
{
    private final String customerName;
    private final MovieResponseDTO movie;
    private final double amount;
    private final int frequentRenterPoints;

    public MovieRentalSummary(MovieResponseDTO movie, String customerName, double amount, int frequentRenterPoints)
    {
        this.customerName = customerName;
        this.movie = movie;
        this.amount = amount;
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public MovieResponseDTO getMovie()
    {
        return movie;
    }

    public double getAmount()
    {
        return amount;
    }

    public int getFrequentRenterPoints()
    {
        return frequentRenterPoints;
    }
}
