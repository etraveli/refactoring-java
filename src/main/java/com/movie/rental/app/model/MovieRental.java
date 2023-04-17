package com.movie.rental.app.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This MovieRental class is used as movie rental data model
 *
 * @author pabasara8857@gmail.com
 */
@Data
@AllArgsConstructor
public class MovieRental {
  @NotNull(message = "Movie id cannot be null")
  @NotBlank(message = "Movie id cannot be empty")
  private String movieId;

  @Min(value = 1, message = "Minimum no of days should be 1")
  private int days;
}
