package com.rentalmovies.moviestore;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.rentalmovies.customer.model.Customer;
import com.rentalmovies.customer.model.CustomerResponseDTO;
import com.rentalmovies.customer.service.CustomerService;
import com.rentalmovies.exceptions.CustomerNotFoundException;
import com.rentalmovies.exceptions.MovieNotFoundException;
import com.rentalmovies.movie.model.MovieResponseDTO;
import com.rentalmovies.movie.model.Movie;
import com.rentalmovies.movie.service.MovieService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The MovieStoreDataAccess class provides an efficient way to access and manipulate
 * movie and customer data. It implements caching mechanisms to improve the performance
 * of data retrieval operations. This can be also atomic reference or act singleton with some 
 * modification. Thread Safety: The LoadingCache from Caffeine is thread-safe,
 * which ensures that the class can be safely used in a multi-threaded environment.
 *
 * This class implements both, MovieService and CustomerService interfaces and delegates
 * the actual service calls while maintaining caches for quick data access.
 * Extracted the logic of storing and retrieving movies to a new MovieStore class to decouple it from RentalInfo.
 * Good practices in this class
 * 1. Immutable (final class with final fields)
 * 2. Dependency Injection
 * 3. Caching
 * 4. The class encapsulates the complexity of caching and data retrieval, providing a clean
 * and efficient interface for accessing movie and customer data.
 * 5. Basic validation checks
 * 6. Use of Optional
 *
 * @author Sajid Riaz
 */
public final class MovieStoreDataAccess implements MovieService, CustomerService
{
    private final MovieService movieService;
    private final CustomerService customerService;
    private final LoadingCache<String, MovieResponseDTO> moviesCache;
    private final LoadingCache<String, CustomerResponseDTO> customersCache;

    public MovieStoreDataAccess(final MovieService movieService, final CustomerService customerService)
    {
        this.movieService = movieService;
        this.customerService = customerService;
        this.moviesCache = Caffeine.newBuilder()
                .maximumSize(100)
                .build(this::loadMovieIntoCache);
        this.customersCache = Caffeine.newBuilder()
                .maximumSize(100)
                .build(this::loadCustomerIntoCache);
    }

    @Override
    public MovieResponseDTO saveMovie(Movie movie)
    {
        if (movie == null)
        {
            return null;
        }
        movieService.saveMovie(movie);
        MovieResponseDTO responseDTO = convertToMovieResponseDTO(movie);
        moviesCache.put(movie.getMovieId(), responseDTO);
        return responseDTO;
    }

    @Override
    public Optional<MovieResponseDTO> getMovieById(String id)
    {
        if (id == null || id.trim().isEmpty())
        {
            return Optional.empty();
        }

        return Optional.ofNullable(moviesCache.get(id, this::loadMovieIntoCache));
    }

    private MovieResponseDTO loadMovieIntoCache(String id)
    {
        return movieService.getMovieById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    private CustomerResponseDTO loadCustomerIntoCache(String id)
    {
        return customerService.getCustomerById(id)
                .orElseThrow(() -> new CustomerNotFoundException("id"));
    }

    private MovieResponseDTO convertToMovieResponseDTO(Movie movie)
    {
        return new MovieResponseDTO(movie.getTitle(), movie.getMovieId(), movie.getMovieType().name());
    }

    @Override
    public List<MovieResponseDTO> getAllMovies()
    {
        return Collections.unmodifiableList(movieService.getAllMovies());
    }

    @Override
    public CustomerResponseDTO saveCustomer(final Customer customer)
    {
        if (customer == null)
        {
            return null;
        }
        CustomerResponseDTO responseDTO = customerService.saveCustomer(customer);
        customersCache.put(customer.getCustomerId(), responseDTO);
        return responseDTO;
    }

    @Override
    public Optional<CustomerResponseDTO> getCustomerById(String id)
    {
        if (id == null || id.trim().isEmpty())
        {
            return Optional.empty();
        }
        return Optional.ofNullable(customersCache.get(id, this::loadCustomerIntoCache));
    }
}
