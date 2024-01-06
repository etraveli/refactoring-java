package com.etraveli.modal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieRentalResponse {

    private Long id;
    private CustomerResponse customer;
    private MovieResponse movie;
    private int days;
}
