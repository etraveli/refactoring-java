package com.example.rentalapi.service.impl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rentalapi.constants.RentalConstants;
import com.example.rentalapi.dao.MovieDao;
import com.example.rentalapi.exception.InvalidMovieCodeException;
import com.example.rentalapi.model.Movie;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @Mock
    private MovieDao movieDao;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_INITIALIZE_DEFAULT_MOVIES)
    void testInitializeDefaultMovies() {
        when(movieDao.saveMovie(anyString(), any(Movie.class))).thenReturn(new Movie("Dummy", RentalConstants.MOVIE_CODE_NEW));
        assertDoesNotThrow(() -> movieService.intializeDefaultMovies());
        verify(movieDao, times(4)).saveMovie(anyString(), any(Movie.class));
    }

    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_SAVE_MOVIES)
    void testSaveMovie_WithValidCode() {
        String code = "F001";
        Movie movie = new Movie("Inception", RentalConstants.MOVIE_CODE_NEW);
        when(movieDao.saveMovie(code, movie)).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(code, movie);
        assertNotNull(savedMovie);
        assertEquals("Inception", savedMovie.getTitle());
        assertEquals(RentalConstants.MOVIE_CODE_NEW, savedMovie.getCode());
        verify(movieDao, times(1)).saveMovie(code, movie);
    }


    
    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_SAVE_MOVIES_WITH_INVALID_CODE)
    void saveMovie_WithInvalidCode_ShouldThrowException() {
        Movie invalidMovie = new Movie("Test Movie", "InvalidCode");
        assertThrows(InvalidMovieCodeException.class, () -> movieService.saveMovie("M001", invalidMovie));
        verify(movieDao, never()).saveMovie(anyString(), any());
    }


    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_GET_ALL_MOVIES)
    void testGetAllMovies_WithExistingMovies() {
        Map<String, Movie> expectedMovies = new HashMap<>();
        expectedMovies.put("F001", new Movie("Inception", RentalConstants.MOVIE_CODE_NEW));
        expectedMovies.put("F002", new Movie("The Dark Knight", RentalConstants.MOVIE_CODE_REGULAR));
        when(movieDao.getAllMovies()).thenReturn(expectedMovies);
        Map<String, Movie> actualMovies = movieService.getAllMovies();
        assertNotNull(actualMovies);
        assertEquals(expectedMovies.size(), actualMovies.size());
        assertTrue(actualMovies.containsKey("F001"));
        assertTrue(actualMovies.containsKey("F002"));
        assertEquals("Inception", actualMovies.get("F001").getTitle());
        assertEquals("The Dark Knight", actualMovies.get("F002").getTitle());
        verify(movieDao, times(1)).getAllMovies();
    }

    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_GET_ALL_MOVIES_EMPTY)
    void testGetAllMovies_WithNoMovies() {
        when(movieDao.getAllMovies()).thenReturn(Collections.emptyMap());
        Map<String, Movie> actualMovies = movieService.getAllMovies();
        assertNotNull(actualMovies);
        assertTrue(actualMovies.isEmpty());
        verify(movieDao, times(1)).getAllMovies();
    }
}
