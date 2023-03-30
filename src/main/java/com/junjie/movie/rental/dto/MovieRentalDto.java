package com.junjie.movie.rental.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRentalDto {
    @NotNull(message = "movieId field in the field of movieRentalDtoList can not be null")
    private Long movieId;
    @NotNull(message = "rentDays field in the field of movieRentalDtoList can not be null")
    private Integer rentDays;
    private double expense;
}
