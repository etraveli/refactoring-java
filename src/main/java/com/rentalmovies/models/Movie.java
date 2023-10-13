package com.rentalmovies.models;

import static com.rentalmovies.utils.MovieUtils.validateString;

import java.util.Objects;

/**
 * Making a class immutable should be a priority, especially in environments where concurrency, security, and data consistency are paramount.
 * Use final keyword with classes when possible to ensure they cannot be subclassed, adding an extra layer of immutability.
 * Ensuring classes are immutable where possible to enhance thread safety and data integrity
 * Validation: Added validation to ensure that we have non-null, valid data to work with, making the system more robust.
 * Encapsulated movie data within the Movie class, making the code more object-oriented and easier to manage.
 */
public final class Movie
{
    private final String title;
    private final MovieType movieType;

    public Movie(final String title, final MovieType type)
    {
        this.title = validateString(title);
        this.movieType = Objects.requireNonNull(type, "Invalid Movie type");
    }

    public String getTitle()
    {
        return title;
    }

    public MovieType getMovieType()
    {
        return movieType;
    }

    @Override
    public String toString()
    {
        return String.format("Movie{title='%s', code='%s'}", title, movieType.name());
    }
}
