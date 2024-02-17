package com.example.rentalapi.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentalapi.constants.RentalConstants;
import com.example.rentalapi.dao.MovieDao;
import com.example.rentalapi.exception.InvalidMovieCodeException;
import com.example.rentalapi.model.Movie;
import com.example.rentalapi.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	MovieDao movieDao;
	
    @Override
    public void intializeDefaultMovies() {
    	movieDao.saveMovie("F001", new Movie("You've Got Mail", RentalConstants.MOVIE_CODE_REGULAR));
    	movieDao.saveMovie("F002", new Movie("Matrix", RentalConstants.MOVIE_CODE_REGULAR));
    	movieDao.saveMovie("F003", new Movie("Cars", RentalConstants.MOVIE_CODE_CHILDRENS));
    	movieDao.saveMovie("F004", new Movie("Fast & Furious X", RentalConstants.MOVIE_CODE_NEW));   
    }
    
    @Override
    public Movie saveMovie(String id, Movie movie) {
    	if (!Movie.isValidCode(movie.getCode())) {
            throw new InvalidMovieCodeException(RentalConstants.INVALID_MOVIE_CODE_EXCEPTION_MESSAGE + movie.getCode());
        }
    	return movieDao.saveMovie(id, movie);
    }

	@Override
	public Map<String, Movie> getAllMovies() {
		return movieDao.getAllMovies();
	}
    
    
}