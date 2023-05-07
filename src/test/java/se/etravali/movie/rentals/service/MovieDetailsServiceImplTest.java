package se.etravali.movie.rentals.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.etraveli.movie.rentals.model.Movie;
import se.etraveli.movie.rentals.model.MovieCode;
import se.etraveli.movie.rentals.repository.MovieRepository;
import se.etraveli.movie.rentals.service.MovieDetailsServiceImpl;

import static org.mockito.Mockito.when;

@SpringBootTest(classes=se.etraveli.movie.rentals.Main.class)
@ExtendWith(MockitoExtension.class)
public class MovieDetailsServiceImplTest {

    private final MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
    private final MovieDetailsServiceImpl movieDetailsService = new MovieDetailsServiceImpl(movieRepository);

    @Test
    public void getMovieCodeTest(){

        when(movieRepository.findMovieByMovieId("F001")).thenReturn(new Movie("Matrix", MovieCode.NEW));

        Assertions.assertEquals(MovieCode.NEW,movieDetailsService.getMovieCode("F001"));
    }

    @Test
    public void getMovieTitleTest(){

        when(movieRepository.findMovieByMovieId("F001")).thenReturn(new Movie("Matrix", MovieCode.NEW));

        Assertions.assertEquals("Matrix",movieDetailsService.getMovieTitle("F001"));
    }
}
