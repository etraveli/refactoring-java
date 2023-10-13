package com.rentalmovies.movie.model;

import com.rentalmovies.movie.model.MovieType;

/**
 * Making a class immutable should be a priority, especially in environments where concurrency, security, and data consistency are paramount.
 * Since DTOs are immutable objects, the data remains consistent after the DTO object has been constructed.
 * The client can’t directly alter the data once the DTO is received.
 * Use final keyword with classes when possible to ensure they cannot be subclassed, adding an extra layer of immutability.
 * Ensuring classes are immutable where possible to enhance thread safety and data integrity
 * Validation: Added validation to ensure that we have non-null, valid data to work with, making the system more robust.
 * Encapsulated movie data within the Movie class, making the code more object-oriented and easier to manage.
 */
public class MovieResponseDTO
{
    private final String movieId;
    private final String title;
    private final String movieType;

    public MovieResponseDTO(String title, String movieId, String movieType)
    {
        this.movieId = movieId;
        this.title = title;
        this.movieType = movieType;
    }

    public String getMovieId()
    {
        return movieId;
    }

    public String getTitle()
    {
        return title;
    }

    public String getMovieType()
    {
        return movieType;
    }

    public MovieType getEnumMovieType(final String movieType)
    {
        return MovieType.fromString(movieType);
    }

    @Override
    public String toString()
    {
        return "MovieResponseDTO{" +
                "movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", movieType='" + movieType + '\'' +
                '}';
    }
}
