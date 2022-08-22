package com.etraveli.service;

import com.etraveli.db.repository.MovieRepository;
import com.etraveli.exception.NoMoviesException;
import com.etraveli.pattern.MovieRentals;
import com.etraveli.pattern.types.Children;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.etraveli.testdata.TestData.getCustomerWithRentals;
import static com.etraveli.testdata.TestData.getMovies;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalInformationServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieRentals movieRentalFactory;

    @InjectMocks
    private RentalInformationServiceImpl rentalInformationService;

    @Test
    public void test_should_return_rental_information_for_customer() {
        when(movieRepository.findAll()).thenReturn(getMovies());
        when(movieRentalFactory.getMovieRentalType(anyString())).thenReturn(Optional.of(new Children()));
        assertEquals(rentalInformationService.statement(getCustomerWithRentals()).size(), 5);
    }

    @Test
    public void test_should_return_rental_information_for_no_movies() {
        when(movieRepository.findAll()).thenReturn(null);
        assertThrows(NoMoviesException.class, () -> rentalInformationService.statement(null));
    }
}