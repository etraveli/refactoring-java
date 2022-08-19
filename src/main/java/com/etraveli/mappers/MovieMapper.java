package com.etraveli.mappers;

import com.etraveli.db.entity.MovieEntity;
import com.etraveli.exception.NoMoviesException;
import com.etraveli.model.Movie;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieMapper {

    private MovieMapper() {

    }

    public static Map<String, Movie> getMovies(List<MovieEntity> savedMovies) {
        if (CollectionUtils.isEmpty(savedMovies)) {
            throw new NoMoviesException("No Movies found in the database");
        }
        return savedMovies.stream()
                .collect(Collectors.toMap(movieEntity -> movieEntity.getId(),
                        movieEntity -> new Movie(movieEntity.getTitle(), movieEntity.getCode())));
    }
}
