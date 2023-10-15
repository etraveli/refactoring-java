package com.rentalmovies.customer.model;

import com.rentalmovies.movierental.model.MovieRentalResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Making a class immutable is beneficial. Since DTOs are immutable objects,
 * the data remains consistent after the DTO object has been constructed.
 * The client can’t directly alter the data once the DTO is received.
 * 1. Thread Safety: Immutable objects are inherently thread-safe
 * 2. It eliminates the need for synchronization, enhancing performance in multi-threaded environments
 * 3. There’s less risk of unintended side-effects and state changes, reducing bugs and errors.
 * 4. Immutable objects can be safely shared and reused across different parts of a program without cloning or copying.
 * 5. Since their state doesn’t change, their hashcodes can be cached, speeding up operations in hash-based collections.
 *
 */
public final class CustomerResponseDTO
{
    private final String id;
    private final String name;
    private final List<MovieRentalResponseDTO> rentals;

    public CustomerResponseDTO(final Customer customer)
    {
        this.id = customer.getCustomerId();
        this.name = customer.getCustomerName();
        this.rentals = customer.getMovieRentals()
                .stream()
                .map(MovieRentalResponseDTO::new)
                .collect(Collectors.toList());
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public List<MovieRentalResponseDTO> getRentals()
    {
        return rentals;
    }

    @Override
    public String toString()
    {
        return "CustomerResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rentals=" + rentals +
                '}';
    }
}
