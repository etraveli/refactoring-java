package com.etraveli.modal.request;

import com.etraveli.constant.UtilConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieRequest {

    private Long movieId;

    @NotBlank(message = UtilConstant.MOVIE_CODE_REQUIRED)
    private String movieCode;

    @NotBlank(message = UtilConstant.MOVIE_TITLE_REQUIRED)
    private String title;

    @NotBlank(message = UtilConstant.CODE_REQUIRED)
    private String code;
}
