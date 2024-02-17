package com.example.rentalapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentalapi.constants.APIConstant;
import com.example.rentalapi.model.Movie;
import com.example.rentalapi.service.MovieService;

@RestController
@RequestMapping(APIConstant.MOVIE_API_BASE_PATH)
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping(APIConstant.SAVE_MOVIE_PATH)
    public ResponseEntity<Movie> saveMovie(@RequestParam String id, @RequestBody Movie movie) {
        Movie savedMovie = movieService.saveMovie(id, movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

}
