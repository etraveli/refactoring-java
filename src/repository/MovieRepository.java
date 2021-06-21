package repository;

import model.Movie;

public interface MovieRepository {

    Movie findById(String id);

    void add(Movie movie);
}
