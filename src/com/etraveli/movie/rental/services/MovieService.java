package com.etraveli.movie.rental.services;

import com.etraveli.movie.rental.exceptions.MovieNotFoundException;
import com.etraveli.movie.rental.models.Movie;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry that helps to keep movies in one and only place to make it more reachable and stable.
 */
public class MovieService {

   public static final Map<String, Movie> movies = new HashMap();

   private MovieService() {
      //
   }
   /**
    * Adding constant movies to movies list.
    */
   public static void addConstantMovies() {
      movies.put("F001", new Movie("F001", "You've Got Mail", Movie.MovieCode.REGULAR));
      movies.put("F002", new Movie("F002", "Matrix", Movie.MovieCode.REGULAR));
      movies.put("F003", new Movie("F003","Cars", Movie.MovieCode.CHILDREN));
      movies.put("F004", new Movie("F004","Fast & Furious X", Movie.MovieCode.NEW));

   }

   public static Movie getMovieByMovieId(final String movieId){
      final Movie movie = movies.get(movieId);

      if (movie == null) {
         throw new MovieNotFoundException(movieId);
      }

      return movie;
   }

}
