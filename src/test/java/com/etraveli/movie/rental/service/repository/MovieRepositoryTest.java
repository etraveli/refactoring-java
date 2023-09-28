package com.etraveli.movie.rental.service.repository;

import com.etraveli.movie.rental.service.dto.Movie;
import com.etraveli.movie.rental.service.util.SampleData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static com.etraveli.movie.rental.service.util.MovieFixture.createMovieList;
import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

    @InjectMocks
    MovieRepository movieRepository;

    @Mock
    SampleData sampleData;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindMovieById() {
        // Prepare
        String movieId = "F001";

        Mockito.when(sampleData.getMovieData())
                .thenReturn(createMovieList());

        // Mock repository behaviour
        Optional<Movie> result =  movieRepository.findMovieById(movieId);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(movieId, result.get().movieId());
        assertEquals("You've Got Mail", result.get().title());
    }

    @Test
    void testFindMovieById_NotFound() {
        // Prepare
        String movieId = "F001";

        Mockito.when(sampleData.getMovieData())
                .thenReturn(Collections.EMPTY_LIST);

        // Mock repository behaviour
        Optional<Movie> result =  movieRepository.findMovieById(movieId);

        // Assertions
        assertFalse(result.isPresent());
    }
}