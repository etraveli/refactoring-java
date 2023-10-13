package com.rentalmovies.movierental.model;

import com.rentalmovies.movie.model.MovieType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * This class is responsible for converting the {@link MovieType} enum to a String so it can be
 * stored in the database, and vice versa. This conversion is necessary as JPA (Java Persistence API)
 * doesn't support the direct storage of complex enums.
 *
 * It's marked with the {@link Converter} annotation to be auto-applied to all entities in the
 * same persistence unit, converting any attribute of type {@link MovieType} to String and
 * vice versa.
 *
 * @author Sajid Riaz
 */
@Converter (autoApply = true)
public class MovieTypeConverter implements AttributeConverter<MovieType, String>
{

    private static final Logger logger = LoggerFactory.getLogger(MovieTypeConverter.class);

    @Override
    public String convertToDatabaseColumn(MovieType attribute)
    {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public MovieType convertToEntityAttribute(String dbData)
    {
        if (dbData == null)
        {
            return null;
        }
        try
        {
            return MovieType.valueOf(dbData);
        }
        catch (IllegalArgumentException e)
        {
            logger.error("Invalid value for MovieType: {}", dbData);
            return null;
        }
    }
}
