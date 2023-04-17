package com.movie.rental.app.model;

import com.movie.rental.app.enums.MovieCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This Movie class is used as movie data model
 *
 * @author pabasara8857@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
public class Movie {
  private String movieId;
  private String title;
  private MovieCode code;

  public Movie(String title, MovieCode code) {
    this.title = title;
    this.code = code;
  }
}
