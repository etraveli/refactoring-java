package com.movie.rental.app.service;

import com.movie.rental.app.exception.ResourceNotFoundException;
import com.movie.rental.app.model.Customer;
import com.movie.rental.app.model.MovieRental;
import com.movie.rental.app.repository.RentalInfoDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RentalInfoServiceTest {

  @SpyBean RentalInfoService rentalInfoService;

  @MockBean RentalInfoDataProvider rentalInfoDataProvider;

  private static final String DATA_PROVIDER = "com.movie.rental.app.service.DataProviderTest";

  @ParameterizedTest
  @MethodSource(DATA_PROVIDER + "#getCustomers")
  void generateRentalInfoStatement_test_success(
      Customer customer, String expectedResult, String testDescription) {
    // given
    when(rentalInfoDataProvider.getMovies()).thenReturn(DataProviderTest.getMovies());

    // when
    String actualResult = rentalInfoService.generateRentalInfoStatement(customer);

    // then
    assertEquals(expectedResult, actualResult, testDescription);
  }

  @Test
  void generateRentalInfoStatement_test_invalid_movie() {
    // given
    when(rentalInfoDataProvider.getMovies()).thenReturn(DataProviderTest.getMovies());

    Customer customer = new Customer("C. U. Stomer", List.of(new MovieRental("F007", 6)));

    // when and then
    assertThrows(
        ResourceNotFoundException.class,
        () -> rentalInfoService.generateRentalInfoStatement(customer));
  }
}
