package com.etraveli.movie.service.repository;

import com.etraveli.movie.service.BaseTest;
import com.etraveli.movie.service.application.DataHolder;
import com.etraveli.movie.service.dto.Movie;
import java.util.Collections;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.etraveli.movie.service.util.TestData.getMovieList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MovieRepositoryTest extends BaseTest {

    @InjectMocks
    private MovieRepository movieRepository;
    @Mock
    private DataHolder dataHolder;

    @Before
    public void setUp() {
        movieRepository = new MovieRepository(dataHolder);
    }

    @Test
    public void testFindMovieByID() {

        Mockito.when(dataHolder.getMovies()).thenReturn(getMovieList());

        // Test finding a movie by ID
        Movie foundMovie = movieRepository.findMovieByID("F001");
        assertNotNull(foundMovie);
        assertEquals("F001", foundMovie.movieCode());
        assertEquals("You've Got Mail", foundMovie.movieName());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindMovieByIDNotFound() {

        Mockito.when(dataHolder.getMovies()).thenReturn(Collections.EMPTY_LIST);
        // Test finding a non-existent movie by ID, expecting an exception
        movieRepository.findMovieByID("F004");
    }
}
