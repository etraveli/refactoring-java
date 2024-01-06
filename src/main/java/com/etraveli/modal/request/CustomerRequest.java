package com.etraveli.modal.request;

import com.etraveli.constant.UtilConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest {

    private Long customerId;

    @NotBlank(message = UtilConstant.NAME_REQUIRED)
    private String name;

    @NotBlank(message = UtilConstant.ID_NUMBER_REQUIRED)
    private String IdNumber;

    private Integer birthYear;
}
