package com.junjie.movie.rental.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    @NotNull(message = "customerId field can not be null")
    private Long customerId;
    @NotNull(message = "movieRentalDtoList field can not be null")
    @Valid
    private List<MovieRentalDto> movieRentalDtoList;
    private int orderFrequentPoints;
    private double totalExpense;
}
