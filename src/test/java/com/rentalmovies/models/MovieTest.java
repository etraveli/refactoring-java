package com.rentalmovies.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovieTest
{
    private Movie movie;
    private MovieType movieType;
    private static final String TITLE = "MovieTitle";

    @BeforeEach
    void init()
    {
        movieType = Mockito.mock(MovieType.class);
        movie = new Movie(TITLE, movieType);
    }

    @Test
    void testMovieCreation()
    {
        assertEquals(TITLE, movie.getTitle());
    }

    @Test
    void testNullMovieType()
    {
        assertThrows(NullPointerException.class, () -> new Movie(TITLE, null));
    }

    @Test
    void testNullMovieTest()
    {
        assertThrows(NullPointerException.class, () -> new Movie(TITLE, null));
    }
}
