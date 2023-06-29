package com.movies.rental.service.repository.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRental {
  private String movieId;
  private double amountPerMovie;
  private int daysOfRental;
}
