package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.request.RentalDetailsRequest;
import com.etraveli.movie.rental.service.response.RentalDetailsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.etraveli.movie.rental.service.commons.CustomerCommon.createCustomerById;
import static com.etraveli.movie.rental.service.commons.CustomerCommon.createMovieRentalResponse;
import static com.etraveli.movie.rental.service.commons.MovieCommon.createMovieById;
import static com.etraveli.movie.rental.service.commons.RentalDetailCommon.createRentalDetailRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieRentalServiceImplTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieRentalServiceImpl movieRentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movieRentalService.setBasePoints(1);
        movieRentalService.setDaysToEarnBonusPoints(2);
        movieRentalService.setGenreToEarnBonusPoints("NEW");
    }

    @Test
    void testGetCustomerRental() {
        // Test data
        Long customerId = 1L;
        Long movieId = 1L;
        String title_1 = "SuperMan";

        RentalDetailsRequest request = createRentalDetailRequest(customerId);
        when(customerService.getCustomerByCustomerId(customerId))
                .thenReturn(createCustomerById(customerId));
        when(movieService.findById(movieId))
                .thenReturn(createMovieById(movieId, title_1));

        // Call the method under test
        RentalDetailsResponse response = createMovieRentalResponse();
        assertEquals("John", response.customer().getName());
        assertEquals(BigDecimal.valueOf(2), response.totalRent());
        assertEquals(2, response.frequentEnterPoints());
        assertEquals("SuperMan", response.rentalLineDetailsResponse().get(0).movieName());
    }
}
