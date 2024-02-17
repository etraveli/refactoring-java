package com.example.rentalapi.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.rentalapi.dao.MovieDao;
import com.example.rentalapi.model.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {

	Map<String, Movie> movies = new HashMap<>();
	@Override
	public Movie saveMovie(String code, Movie movie) {
		movies.put(code, movie);
		return movie;
	}

	@Override
	public Map<String, Movie> getAllMovies(){
		return movies;
	}
}
