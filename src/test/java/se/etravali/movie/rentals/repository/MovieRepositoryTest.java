package se.etravali.movie.rentals.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.etraveli.movie.rentals.model.Movie;
import se.etraveli.movie.rentals.model.MovieCode;
import se.etraveli.movie.rentals.repository.MovieRepository;

@SpringBootTest(classes=se.etraveli.movie.rentals.Main.class)
@ExtendWith(MockitoExtension.class)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void findMovieByMovieIdTest(){

        Assertions.assertEquals(MovieCode.REGULAR, movieRepository.findMovieByMovieId("F001_TEST").getCode());
        Assertions.assertEquals("Matrix", movieRepository.findMovieByMovieId("F002_TEST").getTitle());
        Assertions.assertEquals(MovieCode.CHILDRENS, movieRepository.findMovieByMovieId("F003_TEST").getCode());
        Assertions.assertEquals("Fast & Furious X", movieRepository.findMovieByMovieId("F004_TEST").getTitle());
    }

    @Test
    public void addMovieByMovieIdTest(){

        movieRepository.addMovieById("F005_TEST",new Movie("MOVIE_TEST", MovieCode.NEW));

        Assertions.assertEquals("MOVIE_TEST", movieRepository.findMovieByMovieId("F005_TEST").getTitle());
        Assertions.assertEquals(MovieCode.NEW, movieRepository.findMovieByMovieId("F005_TEST").getCode());
    }

}
