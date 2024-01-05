package com.etraveli.service.impl;

import com.etraveli.exceptiondomain.AlreadyExistException;
import com.etraveli.exceptiondomain.DataNotFoundException;
import com.etraveli.exceptiondomain.MovieIllegalArgumentException;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final Set<String> CODES = Set.of("REGULAR", "CHILDRENS", "NEW");

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieResponse createMovie(MovieRequest movieRequest) {
        logger.info("--ENTER--MovieServiceImpl--createMovie--");
        validateMovie(movieRequest);
        validateMovieCode(movieRequest);

        movieRequest.setMovieCode(movieRequest.getMovieCode().toUpperCase());
        movieRequest.setCode(movieRequest.getCode().toUpperCase());
        Movie movie = this.mapRequestToEntity(movieRequest);
        Movie savedMovie = this.movieRepository.save(movie);

        logger.info("--EXIT--MovieServiceImpl--createMovie--");
        return this.mapEntityToResponse(savedMovie);
    }

    private void validateMovie(MovieRequest movieRequest) {
        logger.info("--ENTER--MovieServiceImpl--validateMovie--");
        if (movieRequest != null && movieRequest.getMovieCode() != null) {
            Movie movieByMovieCode = movieRepository.findMovieByMovieCode(movieRequest.getMovieCode().toUpperCase());
            if (movieByMovieCode != null) {
                logger.error("--ERROR-- validateMovie -- AlreadyExistException-- {}", ExceptionConstant.MOVIE_CODE_EXIST);
                throw new AlreadyExistException(ExceptionConstant.MOVIE_CODE_EXIST);
            }
        }
    }

    private void validateMovieCode(MovieRequest movieRequest) {
        logger.info("--ENTER--MovieServiceImpl--validateMovieCode--");
        if (movieRequest.getCode() != null) {
            if (!CODES.contains(movieRequest.getCode().toUpperCase())) {
                logger.error("--ERROR-- validateMovieCode -- {}", ExceptionConstant.CODE_WRONG + CODES);
                throw new MovieIllegalArgumentException(ExceptionConstant.CODE_WRONG + CODES);
            }
        }
    }

    @Override
    public MovieResponse updateMovie(MovieRequest movieRequest) {
        logger.info("--ENTER--MovieServiceImpl--updateMovie--");
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
        logger.info("--ENTER-- getMovieByMovieCode --");
        Movie movie = this.movieRepository.findMovieByMovieCode(movieCode);
        if (movie == null) {
            logger.error("--ERROR--[GET]--DataNotFoundException--{}", ExceptionConstant.MOVIE_NOT_FOUND);
            throw new DataNotFoundException(ExceptionConstant.MOVIE_NOT_FOUND);
        }
        return mapEntityToResponse(movie);
    }

    @Override
    public Movie findMovieByMovieCode(String movieCode) {
        logger.info("--ENTER-- findMovieByMovieCode --");
        Movie movie = this.movieRepository.findMovieByMovieCode(movieCode);
        if (movie == null) {
            logger.error("--ERROR--[GET]--DataNotFoundException--{}", ExceptionConstant.MOVIE_NOT_FOUND);
            throw new DataNotFoundException(ExceptionConstant.MOVIE_NOT_FOUND);
        }
        return movie;
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        logger.info("--ENTER-- getAllMovies --");
        List<Movie> moviesList = this.movieRepository.findAll();

        return moviesList.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getAllMoviesList() {
        logger.info("--ENTER-- getAllMoviesList --");
        return this.movieRepository.findAll();
    }

    private void updateMovieDetails(Movie movie, MovieRequest movieRequest) {
        logger.info("--ENTER-- updateMovieDetails --");
        if (movieRequest.getTitle() != null) {
            movie.setTitle(movieRequest.getTitle());
        }

        if (movieRequest.getCode() != null) {
            movie.setCode(movieRequest.getCode());
        }
        logger.info("--EXIT-- updateMovieDetails --");
    }

    private Movie mapRequestToEntity(MovieRequest movieRequest) {
        return modelMapper.map(movieRequest, Movie.class);
    }

    private MovieResponse mapEntityToResponse(Movie movie) {
        return modelMapper.map(movie, MovieResponse.class);
    }
}
