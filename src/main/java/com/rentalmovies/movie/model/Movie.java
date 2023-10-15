package com.rentalmovies.movie.model;

import com.rentalmovies.movierental.model.MovieTypeConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.rentalmovies.utils.MovieUtils.validateString;

import java.util.Objects;

/**
 * Making a class immutable should be a priority, especially in environments where concurrency, security, and data consistency are paramount.
 * Use final keyword with classes when possible to ensure they cannot be subclassed, adding an extra layer of immutability.
 * Ensuring classes are immutable where possible to enhance thread safety and data integrity
 * Validation: Added validation to ensure that we have non-null, valid data to work with, making the system more robust.
 * Encapsulated movie data within the Movie class, making the code more object-oriented and easier to manage.
 *
 *
 * Cannot make it Immutable because it is JPA limitations
 */
@Entity
@Table (name = "movies")
public class Movie
{
    @Id
    private String movieId;
    private String title;

    @Convert (converter = MovieTypeConverter.class)
    private MovieType movieType;

    public Movie()
    {
        // Default no-argument constructor required by JPA
    }

    public Movie(final String title, final String movieId, final MovieType type)
    {
        this(title, type);
        this.movieId = movieId;
    }

    public Movie(final String title, final MovieType type)
    {
        this.title = validateString(title);
        this.movieType = Objects.requireNonNull(type, "Invalid Movie type");
    }

    public String getMovieId()
    {
        return movieId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public MovieType getMovieType()
    {
        return movieType;
    }

    public void setMovieType(MovieType movieType)
    {
        this.movieType = movieType;
    }

    @Override
    public String toString()
    {
        return String.format("Movie{title='%s', code='%s'}", title, movieType.name());
    }
}
