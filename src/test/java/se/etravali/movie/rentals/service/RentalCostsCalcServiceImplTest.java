package se.etravali.movie.rentals.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.etraveli.movie.rentals.model.MovieCode;
import se.etraveli.movie.rentals.model.MovieRental;
import se.etraveli.movie.rentals.model.MovieRentalCosts;
import se.etraveli.movie.rentals.service.MovieDetailsService;
import se.etraveli.movie.rentals.service.MovieDetailsServiceImpl;
import se.etraveli.movie.rentals.service.RentalCostsCalcServiceImpl;
import se.etraveli.movie.rentals.business.bonus.FrequentEnterPoints;
import se.etraveli.movie.rentals.business.bonus.FrequentEnterPointsImpl;
import static org.mockito.Mockito.when;

@SpringBootTest(classes=se.etraveli.movie.rentals.Main.class)
@ExtendWith(MockitoExtension.class)
public class RentalCostsCalcServiceImplTest {

    private final MovieDetailsService movieDetailsService = Mockito.mock(MovieDetailsServiceImpl.class);
    private final FrequentEnterPoints frequentEnterPointsService = Mockito.mock(FrequentEnterPointsImpl.class);

    private final RentalCostsCalcServiceImpl rentalCostsCalcService = new RentalCostsCalcServiceImpl(movieDetailsService, frequentEnterPointsService);

    @Test
    public void calcRentalAmountAndBonusTest(){

        MovieRental movieRental = new MovieRental("F001", 2);

        when(movieDetailsService.getMovieCode("F001"))
                .thenReturn(MovieCode.NEW);

        when(frequentEnterPointsService.getFrequentEnterPoints(MovieCode.NEW, 2))
                .thenReturn(2);

        MovieRentalCosts movieRentalCosts = rentalCostsCalcService.calcRentalAmountAndBonus(movieRental);

        Assertions.assertEquals(6.0, movieRentalCosts.getCost());
        Assertions.assertEquals(2, movieRentalCosts.getBonus());
        Assertions.assertEquals("F001", movieRentalCosts.getMovieRental().getMovieId());
        Assertions.assertEquals(2, movieRentalCosts.getMovieRental().getDays());
    }
}
