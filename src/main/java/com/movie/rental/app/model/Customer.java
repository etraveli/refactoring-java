package com.movie.rental.app.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * This Customer class is used as customer data model
 *
 * @author pabasara8857@gmail.com
 */
@Data
@AllArgsConstructor
public class Customer {
  @NotNull(message = "Customer name cannot be null")
  @NotBlank(message = "Customer name cannot be empty")
  private String name;

  @Valid private List<MovieRental> rentals;
}
