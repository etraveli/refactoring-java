package com.etraveli.assignments.refactoring.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.etraveli.assignments.refactoring.exception.MovieNotFoundException;
import com.etraveli.assignments.refactoring.util.MovieCategory;
import org.junit.jupiter.api.Test;

class MovieRepositoryTest {

  private final MovieRepository testMovieRepository = new MovieRepository();

  @Test
  void getMovie() {
    var movie = testMovieRepository.getMovie("F001");
    assertNotNull(movie);
    assertEquals("F001", movie.movieId());
    assertEquals("You've Got Mail", movie.title());
    assertEquals(MovieCategory.REGULAR, movie.movieCategory());
  }

  @Test
  void getNonExistingMovie() {
    var thrown =
            assertThrows(
                    MovieNotFoundException.class,
                    () -> {
                      var movie = testMovieRepository.getMovie("NONE");
                    });
    assertEquals("Movie not found for Id NONE", thrown.getMessage());
  }
}
