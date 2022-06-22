package se.etraveli.service;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import se.etraveli.dto.MovieType;
import se.etraveli.entity.Customer;
import se.etraveli.entity.CustomerMovie;
import se.etraveli.entity.Movie;
import org.junit.jupiter.api.Test;
import se.etraveli.repository.CustomerMovieRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

public class CustomerMovieServiceTest {
    private final CustomerMovieRepository customerRepository = Mockito.spy(CustomerMovieRepository.class);
    private final CustomerMovieService customerMovieService = new CustomerMovieServiceImpl(customerRepository);

    @Test
    public void createCustomerMovieAllTest() {
        var customerMovie = new CustomerMovie();
        var customerMovie2 = new CustomerMovie();
        var customer = new Customer();
        var movie = new Movie();
        List<CustomerMovie> customerMovies = new ArrayList<>();
        movie.setMovieType(MovieType.CHILDRENS);
        movie.setCode("23");
        movie.setTitle("title");
        customer.setName("name");
        customerMovie2.setMovie(movie);
        customerMovie2.setCustomer(customer);
        customerMovie2.setPrice(2.0);
        customerMovie.setMovie(movie);
        customerMovie.setCustomer(customer);
        customerMovie.setPrice(2.0);
        customerMovies.add(customerMovie2);
        customerMovies.add(customerMovie);
        customerMovieService.createCustomerMovieAll(customerMovies);

        doReturn(customerMovies).when(customerRepository).saveAll(Mockito.any());
        List<CustomerMovie> customerMovieAll = customerMovieService.createCustomerMovieAll(customerMovies);

        Assertions.assertEquals(customerMovieAll, customerMovies);
    }
}
