package se.etraveli.converter;

import se.etraveli.dto.MovieType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MovieTypeConverter implements AttributeConverter<MovieType, String> {

    @Override
    public String convertToDatabaseColumn(MovieType movieType) {
        return movieType.toString();
    }

    @Override
    public MovieType convertToEntityAttribute(String s) {
        return MovieType.valueOf(s.toUpperCase());
    }
}
