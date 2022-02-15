package com.etraveligroup.movierental.dao;

import java.util.List;

import com.etraveligroup.movierental.models.Movie;

/**
 * @author LMOPURI
 *
 */
public interface MoviesDao {

	List<Movie> getAllMovies();

	Movie getMovieDetails(String code);

	boolean storeMovie(Movie movie);

}
