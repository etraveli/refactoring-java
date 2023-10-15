package com.rentalmovies.moviestore;

import com.rentalmovies.customer.repository.CustomerDaoImpl;
import com.rentalmovies.customer.service.CustomerService;
import com.rentalmovies.customer.service.CustomerServiceImpl;
import com.rentalmovies.exceptions.MovieNotFoundException;
import com.rentalmovies.movie.repository.MovieDaoImpl;
import com.rentalmovies.movie.service.MovieService;
import com.rentalmovies.movie.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class MovieStoreTest
{
    private MovieStoreDataAccess store;

    @BeforeEach
    void init()
    {
        CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());
        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl());
        store = new MovieStoreDataAccess(movieService, customerService);
    }

    @Test
    void getMovieReturnsMovieForValidId()
    {
        assertEquals("You've Got Mail", store.getMovieById(("F001")).get().getTitle());
    }

    @Test
    void getMovieThrowsExceptionForInvalidId()
    {
        assertThrows(MovieNotFoundException.class, () -> store.getMovieById("F999").get());
    }
}
