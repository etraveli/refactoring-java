package repositoryImpl;

import constant.ShowType;
import entity.Movie;
import entity.MovieType;
import org.junit.jupiter.api.Test;
import repository.MovieRepository;
import serviceImpl.RegularPrice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MovieRepositoryImplTest {
    @Test
    public void given_getMovie_When_MovieIdExist_Then_ReturnMovie() {
        MovieRepository repository = new MovieRepositoryImpl();
        MovieType regularMovieType = new MovieType(1L, ShowType.REGULAR, new RegularPrice());
        Movie expected = new Movie(1L, "You've Got Mail", regularMovieType);

        Movie result = repository.getMovie("F001");

        assertEquals(result.getMovieId(), expected.getMovieId());
        assertEquals(result.getMovieType().getMovieTypeID(), expected.getMovieType().getMovieTypeID());
        assertEquals(result.getTitle(), expected.getTitle());
    }

    @Test
    public void given_getMovie_When_MovieIdIsNull_Then_ReturnNull() {
        MovieRepository repository = new MovieRepositoryImpl();
        Movie result = repository.getMovie(null);

        assertNull(result);
    }

    @Test
    public void given_getMovie_When_MovieIdNotExist_Then_ReturnNull() {
        MovieRepository repository = new MovieRepositoryImpl();
        Movie result = repository.getMovie("NotExist");

        assertNull(result);
    }

}