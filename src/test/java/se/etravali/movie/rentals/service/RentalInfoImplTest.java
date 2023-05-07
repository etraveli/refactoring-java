package se.etravali.movie.rentals.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.etraveli.movie.rentals.constants.Constants;
import se.etraveli.movie.rentals.exception.InvalidCustomerException;
import se.etraveli.movie.rentals.exception.MovieRentalException;
import se.etraveli.movie.rentals.model.Customer;
import se.etraveli.movie.rentals.model.MovieRental;
import se.etraveli.movie.rentals.model.MovieRentalCosts;
import se.etraveli.movie.rentals.service.MovieDetailsService;
import se.etraveli.movie.rentals.service.MovieDetailsServiceImpl;
import se.etraveli.movie.rentals.service.RentalInfo;
import se.etraveli.movie.rentals.service.RentalCostsCalcService;
import se.etraveli.movie.rentals.service.RentalCostsCalcServiceImpl;
import java.util.Arrays;
import static org.mockito.Mockito.when;

@SpringBootTest(classes=se.etraveli.movie.rentals.Main.class)
@ExtendWith(MockitoExtension.class)
public class RentalInfoImplTest {

    private final MovieDetailsService movieService = Mockito.mock(MovieDetailsServiceImpl.class);
    private final RentalCostsCalcService rentalCostsCalcService = Mockito.mock(RentalCostsCalcServiceImpl.class);

    private final RentalInfo rentalInfo = new RentalInfo(movieService, rentalCostsCalcService);

    @Test
    public void invalidCustomer1(){
        Throwable exception = Assertions.assertThrows(InvalidCustomerException.class, () -> rentalInfo.statement(null));
        Assertions.assertEquals(Constants.EMPTY_CUSTOMER, exception.getMessage());
    }

    @Test
    public void invalidCustomer2() {
        Throwable exception = Assertions.assertThrows(InvalidCustomerException.class, () -> rentalInfo.statement(new Customer(null, null)));
        Assertions.assertEquals(Constants.EMPTY_CUSTOMER_NAME, exception.getMessage());
    }

    @Test
    public void invalidMovieRental1() {
        Throwable exception = Assertions.assertThrows(MovieRentalException.class, () -> rentalInfo.statement(new Customer("TEST_USER", null)));
        Assertions.assertEquals(Constants.EMPTY_MOVIE_RENTALS, exception.getMessage());
    }

    @Test
    public void invalidMovieRental2() {
        Throwable exception = Assertions.assertThrows(MovieRentalException.class, () -> rentalInfo.statement(new Customer("TEST_USER", Arrays.asList(
                new MovieRental("F001", 0), new MovieRental("F002", 3)
        ))));
        Assertions.assertEquals(Constants.INVALID_MOVIE_RENTAL, exception.getMessage());
    }

    @Test
    public void validCustomerTest(){

        MovieRental rental = new MovieRental("F001", 3);

        MovieRentalCosts movieRentalCosts = new MovieRentalCosts(rental);
        movieRentalCosts.setBonus(1);
        movieRentalCosts.setCost(3.5);

        MovieRental rental2 = new MovieRental("F002", 1);

        MovieRentalCosts movieRentalCosts2 = new MovieRentalCosts(rental2);
        movieRentalCosts2.setBonus(1);
        movieRentalCosts2.setCost(2.0);

        when(rentalCostsCalcService.calcRentalAmountAndBonus(rental)).thenReturn(movieRentalCosts);
        when(rentalCostsCalcService.calcRentalAmountAndBonus(rental2)).thenReturn(movieRentalCosts2);

        when(movieService.getMovieTitle("F001")).thenReturn("You've Got Mail");
        when(movieService.getMovieTitle("F002")).thenReturn("Matrix");

        Customer customer = new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1)));

        String result = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        Assertions.assertEquals(result, rentalInfo.statement(customer));
    }
}
