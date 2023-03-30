package com.junjie.movie.rental.controller;

import com.junjie.movie.rental.dto.MovieDto;
import com.junjie.movie.rental.dto.converter.MovieConverter;
import com.junjie.movie.rental.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/movie")
@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/{movieId}")
    public MovieDto getMovieById(@PathVariable("movieId") Long movieId) {
        return MovieConverter.convertModel2Dto(movieService.findMovieById(movieId));
    }

    @GetMapping
    public List<MovieDto> getAllMovies() {
        var list = new ArrayList<MovieDto>();
        movieService.findAll().stream().forEach(movie ->
                list.add(MovieConverter.convertModel2Dto(movie)));
        return list;
    }

    @PostMapping
    public List<MovieDto> createMovie(@Valid @RequestBody List<MovieDto> movieDtos) {
        var list = new ArrayList<MovieDto>();
        movieDtos.stream().forEach(movieDto ->
                list.add(MovieConverter.convertModel2Dto(
                        movieService.createMovie(MovieConverter.convertDto2Model(movieDto)))));
        return list;
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovieById(movieId);
    }
}
