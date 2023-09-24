package com.etraveli.movie.service.repository;

import com.etraveli.movie.service.application.DataHolder;
import com.etraveli.movie.service.dto.Movie;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final DataHolder dataHolder;

    public Movie findMovieByID(String code) {
        return getSampleMovieData().stream()
                .filter(movie -> movie.movieCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No movie found with ID: " + code));
    }

    private List<Movie> getSampleMovieData() {
        return dataHolder.getMovies();
    }
}