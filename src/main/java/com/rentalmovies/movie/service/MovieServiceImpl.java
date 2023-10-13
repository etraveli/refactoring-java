package com.rentalmovies.movie.service;

import com.rentalmovies.movie.model.MovieResponseDTO;
import com.rentalmovies.movie.model.Movie;
import com.rentalmovies.movie.repository.MovieDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService
{
    private final MovieDao movieDao;

    public MovieServiceImpl(final MovieDao movieDao)
    {
        this.movieDao = movieDao;
    }

    @Override
    public MovieResponseDTO saveMovie(Movie movie)
    {
        movieDao.save(movie);
        return new MovieResponseDTO(movie.getTitle(), movie.getMovieId(), movie.getMovieType().name());
    }

    @Override
    public Optional<MovieResponseDTO> getMovieById(String id)
    {
        return Optional.ofNullable(movieDao.findById(id))
                .map(movie -> new MovieResponseDTO(movie.getTitle(), movie.getMovieId(),
                        movie.getMovieType().name()));
    }

    @Override
    public List<MovieResponseDTO> getAllMovies()
    {
        List<MovieResponseDTO> list = new ArrayList<>();
        for (Movie movie : movieDao.findAll())
        {
            list.add(new MovieResponseDTO(movie.getTitle(), movie.getMovieId(), movie.getMovieType().name()));
        }
        return Collections.unmodifiableList(list);
    }
}
