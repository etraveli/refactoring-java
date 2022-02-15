package com.etraveligroup.movierental.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.etraveligroup.movierental.dao.MoviesDao;
import com.etraveligroup.movierental.enums.MovieType;
import com.etraveligroup.movierental.models.Movie;

/**
 * @author LMOPURI
 *
 */
public class MoviesDaoImpl implements MoviesDao {

	List<Movie> moviesList = new ArrayList<Movie>();

	public MoviesDaoImpl() {
		moviesList.add(new Movie("F001", "You've Got Mail", MovieType.REGULAR));
		moviesList.add(new Movie("F002", "Matrix", MovieType.REGULAR));
		moviesList.add(new Movie("F003", "Cars", MovieType.CHILDRENS));
		moviesList.add(new Movie("F004", "Fast & Furious X", MovieType.NEW));
	}

	public List<Movie> getAllMovies() {
		return this.moviesList;
	}

	public Movie getMovieDetails(String id) {
		return moviesList.stream().filter(movie -> id.equalsIgnoreCase(movie.getId())).findAny().orElse(null);
	}

	public boolean storeMovie(Movie movie) {
		return moviesList.add(movie);
	}

}
