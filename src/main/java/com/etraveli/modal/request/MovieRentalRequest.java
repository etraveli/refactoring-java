package com.etraveli.modal.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieRentalRequest {

    private Long id;
    private Long customerId;
    private String movieCode;
    private int days;
}
