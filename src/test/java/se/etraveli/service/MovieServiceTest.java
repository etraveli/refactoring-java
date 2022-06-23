package se.etraveli.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.etraveli.dto.MovieType;
import se.etraveli.entity.Movie;
import se.etraveli.exception.MovieNotFoundException;
import se.etraveli.repository.MovieRepository;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieServiceTest {
    private final MovieRepository movieRepository = Mockito.spy(MovieRepository.class);
    private final MessageSourceService messageSourceService =  Mockito.spy(MessageSourceService.class);
    private final MovieService movieService = new MovieServiceImpl(movieRepository, messageSourceService);

    @Test
    public void getMovieByCodeTest() {
        Optional<Movie> movie = Optional.of(new Movie(12L, "123", "123", MovieType.NEW));
        doReturn(movie).when(movieRepository).findMovieByCode(Mockito.any());

        Movie movieByCode = movieService.getMovieByCode("123");
        Assertions.assertEquals(movieByCode, movie.get());

        Mockito.when(movieRepository.findMovieByCode(Mockito.any())).thenThrow(MovieNotFoundException.class);
        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieByCode("123"));
    }
}
