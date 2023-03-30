package com.junjie.movie.rental.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    @NotNull(message = "movie name can not be null")
    private String name;
    @NotNull(message = "movie type can not be null")
    private String type;
}
