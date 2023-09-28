package com.etraveli.movie.rental.service.repository;

import com.etraveli.movie.rental.service.dto.Movie;
import com.etraveli.movie.rental.service.util.SampleData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final SampleData sampleData;

    public Optional<Movie> findMovieById(String movieId) {
        return sampleData.getMovieData().stream()
                .filter(movie -> movie.movieId().equals(movieId))
                .findFirst();
    }
}