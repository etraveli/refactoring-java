package com.etraveli.modal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieResponse {

    private Long movieId;
    private String movieCode;
    private String title;
    private String code;
}
