package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.configuration.MovieRentalConfiguration;
import com.etraveli.movie.rental.service.dto.MovieRentalRequest;
import com.etraveli.movie.rental.service.dto.MovieRentalResponse;
import com.etraveli.movie.rental.service.repository.CustomerRepository;
import com.etraveli.movie.rental.service.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;
import static com.etraveli.movie.rental.service.util.CommonFixture.createMovieRentalRequest;
import static com.etraveli.movie.rental.service.util.CommonFixture.createRentalFee;
import static com.etraveli.movie.rental.service.util.CustomerFixture.createCustomerById;
import static com.etraveli.movie.rental.service.util.MovieFixture.createMovieById;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovieRentalServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieRentalConfiguration movieRentalConfiguration;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private MovieRentalService rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rentalService.setDefaultPoints(1);
        rentalService.setExtraPointsEligibleDays(2);
        rentalService.setExtraPointsEligibleType(MovieRentalConfiguration.MovieCode.NEW);
    }

    @Test
    void testGetMovieRentalResponse() {
        // Prepare
        String customerId = "1";
        String movieId_1 = "F001";
        String movieId_2 = "F002";
        String title_1 = "You've Got Mail";
        String title_2 = "Matrix";

        MovieRentalRequest request = createMovieRentalRequest(customerId);

        // Mock repository behavior
        when(customerRepository.findCustomerById(customerId))
                .thenReturn(createCustomerById(customerId));

        when(movieRepository.findMovieById(movieId_1))
                .thenReturn(Optional.of(createMovieById(movieId_1, title_1)));

        when(movieRepository.findMovieById(movieId_2))
                .thenReturn(Optional.of(createMovieById(movieId_2, title_2)));

        when(movieRentalConfiguration.findRentalFeeByMovieCode(MovieRentalConfiguration.MovieCode.REGULAR))
                .thenReturn(createRentalFee());

        // Call the method under test
        MovieRentalResponse response = rentalService.getMovieRentalResponse(request);

        // Assertions
        assertEquals("C. U. Stomer", response.customer().name());
        assertEquals(BigDecimal.valueOf(4), response.totalRental());
        assertEquals(2, response.frequentEnterPoints());
        assertEquals("You've Got Mail", response.movieRentalResponseLines().get(0).movieName());
        assertEquals("Matrix", response.movieRentalResponseLines().get(1).movieName());

        // Verify that the methods of the mocked dependencies were called as expected
        verify(customerRepository, times(1)).findCustomerById(customerId);
        verify(movieRepository, times(1)).findMovieById(movieId_1);
        verify(movieRepository, times(1)).findMovieById(movieId_2);
        verify(movieRentalConfiguration, times(2)).findRentalFeeByMovieCode(MovieRentalConfiguration.MovieCode.REGULAR);
    }
}