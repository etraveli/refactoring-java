package com.example.rentalapi.service;

import java.util.Map;

import com.example.rentalapi.model.Movie;

public interface MovieService {

	public void intializeDefaultMovies();

	public Movie saveMovie(String id, Movie movie);
    
    public Map<String, Movie> getAllMovies();
}