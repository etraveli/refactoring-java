package com.rentalmovies.moviestore;

import com.rentalmovies.exceptions.MovieNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieStoreTest
{

    private MovieStore store;

    @BeforeEach
    void init()
    {
        store = new MovieStore();
    }

    @Test
    void getMovieReturnsMovieForValidId()
    {
        assertEquals("You've Got Mail", store.getMovie("F001").getTitle());
    }

    @Test
    void getMovieThrowsExceptionForInvalidId()
    {
        assertThrows(MovieNotFoundException.class, () -> store.getMovie("F999"));
    }
}
