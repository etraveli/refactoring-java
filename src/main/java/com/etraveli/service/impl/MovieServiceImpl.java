package com.etraveli.service.impl;

import com.etraveli.exceptiondomain.DataNotFoundException;
import com.etraveli.exceptiondomain.RequestNotValidException;
import com.etraveli.exceptiondomain.constant.ExceptionConstant;
import com.etraveli.modal.Movie;
import com.etraveli.modal.request.MovieRequest;
import com.etraveli.modal.response.MovieResponse;
import com.etraveli.repository.MovieRepository;
import com.etraveli.service.MovieService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieResponse createMovie(MovieRequest movieRequest) {

        Movie movie = this.mapRequestToEntity(movieRequest);
        Movie savedMovie = this.movieRepository.save(movie);

        if (savedMovie == null) {
//            throw new Exception()
        }
        return this.mapEntityToResponse(savedMovie);
    }

    @Override
    public MovieResponse updateMovie(MovieRequest movieRequest) {
        if (movieRequest == null && movieRequest.getMovieId() == null) {
            logger.error("--ERROR--MovieServiceImpl--updateMovie -- {}", ExceptionConstant.REQUEST_CANNOT_EMPTY);
            throw new RequestNotValidException(ExceptionConstant.REQUEST_CANNOT_EMPTY);
        }

        Movie movie = this.movieRepository.findMovieByMovieId(movieRequest.getMovieId());

        if (movie == null) {
            logger.error("--ERROR--MovieServiceImpl--updateMovie--{}", ExceptionConstant.CUSTOMER_NOT_FOUND);
            throw new DataNotFoundException(ExceptionConstant.CUSTOMER_NOT_FOUND);
        }

        updateMovieDetails(movie, movieRequest);

        Movie updatedMovie = this.movieRepository.save(movie);
        MovieResponse movieResponse = mapEntityToResponse(updatedMovie);

        logger.info("--EXIT--MovieServiceImpl--updateBookmark--");
        return movieResponse;
    }

    @Override
    public MovieResponse getMovieByMovieCode(String movieCode) {
        Movie movie = this.movieRepository.findMovieByMovieCode(movieCode);
        if (movie == null) {
            logger.error("--ERROR--[GET]--DataNotFoundException--{}", ExceptionConstant.MOVIE_NOT_FOUND);
            throw new DataNotFoundException(ExceptionConstant.MOVIE_NOT_FOUND);
        }
        return mapEntityToResponse(movie);
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        List<Movie> moviesList = this.movieRepository.findAll();

        List<MovieResponse> movieResponseList = moviesList.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());

        return movieResponseList;
    }

    private void updateMovieDetails(Movie movie, MovieRequest movieRequest) {
        logger.info("--ENTER-- updateMovieDetails --");
        if (movieRequest.getTitle() != null) {
            movie.setTitle(movieRequest.getTitle());
        }

        if (movieRequest.getCode() != null) {
            movie.setCode(movieRequest.getCode());
        }
    }

    private Movie mapRequestToEntity(MovieRequest movieRequest) {
        return modelMapper.map(movieRequest, Movie.class);
    }

    private MovieResponse mapEntityToResponse(Movie movie) {
        return modelMapper.map(movie, MovieResponse.class);
    }
}
