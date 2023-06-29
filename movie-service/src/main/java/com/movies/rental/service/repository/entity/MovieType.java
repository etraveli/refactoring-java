package com.movies.rental.service.repository.entity;

import lombok.Getter;

@Getter
public enum MovieType {
  NEW("NEW", 0, 0, 3),
  REGULAR("REGULAR", 2, 2, 1.5),
  CHILDRENS("CHILDRENS", 1.5, 3, 1.5);

  private final String movieTypeName;
  private final double amountPerMovie;
  private final int daysOfRental;
  private final double pricePerDay;

  MovieType(String movieTypeName, double amountPerMovie, int daysOfRental, double pricePerDay) {
    this.movieTypeName = movieTypeName;
    this.amountPerMovie = amountPerMovie;
    this.daysOfRental = daysOfRental;
    this.pricePerDay = pricePerDay;
  }

  public static MovieType getMovieType(String movieTypeName) {
    for (MovieType movieType : MovieType.values()) {
      if (movieType.getMovieTypeName().equals(movieTypeName)) {
        return movieType;
      }
    }
    return null;
  }
}
