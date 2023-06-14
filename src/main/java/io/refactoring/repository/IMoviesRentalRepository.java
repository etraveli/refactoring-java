package io.refactoring.repository;

import io.refactoring.model.Movie;

import java.util.Map;

public interface IMoviesRentalRepository {

    public Movie movie(String movieId);

}
