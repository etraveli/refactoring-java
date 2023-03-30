package com.junjie.movie.rental.dto.converter;

import com.junjie.movie.rental.dto.MovieDto;
import com.junjie.movie.rental.entity.Movie;
import com.junjie.movie.rental.entity.enums.MovieType;

public class MovieConverter {
    public static MovieDto convertModel2Dto(Movie movie) {
        return new MovieDto(movie.getId(),
                movie.getName(),
                movie.getType().getValue());
    }

    public static Movie convertDto2Model(MovieDto movieDto) {
        return new Movie(movieDto.getName(),
                MovieType.valueof(movieDto.getType().trim().toLowerCase()));
    }
}
