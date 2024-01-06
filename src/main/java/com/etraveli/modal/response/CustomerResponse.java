package com.etraveli.modal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponse {

    private Long customerId;
    private String name;
    private String IdNumber;
    private Integer birthYear;
}
