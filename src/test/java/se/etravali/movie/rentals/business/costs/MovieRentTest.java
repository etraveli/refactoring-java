package se.etravali.movie.rentals.business.costs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.etraveli.movie.rentals.business.costs.MovieRent;
import se.etraveli.movie.rentals.model.MovieCode;

@SpringBootTest(classes=se.etraveli.movie.rentals.Main.class)
@ExtendWith(MockitoExtension.class)
public class MovieRentTest {


    @Test
    public void regularMovieRentAmount(){
        Assertions.assertEquals(2.0, MovieRent.movieRentAmount(MovieCode.REGULAR, 2));
        Assertions.assertEquals(5.0, MovieRent.movieRentAmount(MovieCode.REGULAR, 4));
    }

    @Test
    public void newMovieRentAmount(){
        Assertions.assertEquals(6.0, MovieRent.movieRentAmount(MovieCode.NEW, 2));
        Assertions.assertEquals(12.0, MovieRent.movieRentAmount(MovieCode.NEW, 4));
    }

    @Test
    public void childrensMovieRentAmount(){
        Assertions.assertEquals(1.5, MovieRent.movieRentAmount(MovieCode.CHILDRENS, 2));
        Assertions.assertEquals(3.0, MovieRent.movieRentAmount(MovieCode.CHILDRENS, 4));
    }
}
