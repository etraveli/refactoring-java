package com.example.rentalapi.dao;

import java.util.Map;

import com.example.rentalapi.model.Movie;

public interface MovieDao {
	public Movie saveMovie(String code, Movie movie);
	public Map<String, Movie> getAllMovies();
}
