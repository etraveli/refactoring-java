package com.etraveli.movie.service.service;

import com.etraveli.movie.service.application.DataHolder;
import com.etraveli.movie.service.dto.MovieType;
import com.etraveli.movie.service.dto.RentOrder;
import com.etraveli.movie.service.dto.RentOrderResponse;
import com.etraveli.movie.service.dto.RentalFee;
import com.etraveli.movie.service.BaseTest;
import com.etraveli.movie.service.repository.CustomerRepository;
import com.etraveli.movie.service.repository.MovieRepository;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.etraveli.movie.service.util.TestData.getCustomerList;
import static com.etraveli.movie.service.util.TestData.getMovieList;
import static com.etraveli.movie.service.util.TestData.getRentOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MovieRentalServiceTest extends BaseTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private DataHolder staticDataHolder;
    @InjectMocks
    private MovieRentalService movieRentalService;

    @Before
    public void setUp() {
        movieRentalService = new MovieRentalService(movieRepository, customerRepository, staticDataHolder);
    }


    @Test
    public void testCreateRentOrderResponseWithValidOrder() {
        // Prepare test data
        RentOrder order = getRentOrder("001");
        when(customerRepository.findCustomerByID(order.customerID())).thenReturn(getCustomerList().stream()
                .filter(customer -> customer.customerID().equals(order.customerID()))
                .findFirst()
                .orElse(null)
        );

        when(movieRepository.findMovieByID("F001")).thenReturn(getMovieList().stream()
                .filter(movie -> movie.movieCode().equals("F001"))
                .findFirst()
                .orElse(null)
        );

        when(movieRepository.findMovieByID("F002")).thenReturn(getMovieList().stream()
                .filter(movie -> movie.movieCode().equals("F002"))
                .findFirst()
                .orElse(null)
        );

        when(staticDataHolder.getRentalFeeByCode(MovieType.REGULAR))
                .thenReturn(RentalFee.builder()
                        .flatFee(BigDecimal.ZERO)
                        .flatFeeLimit(0)
                        .dailyFee(BigDecimal.valueOf(3))
                        .build());

        // Execute the method
        RentOrderResponse response = movieRentalService.createRentOrderResponse(order);

        // Verify the result
        assertEquals(2, response.rentOrderLineList().size());
        assertEquals(new BigDecimal("15"), response.totalRental());
        assertEquals(2, response.frequentEnterPoints());
    }
}