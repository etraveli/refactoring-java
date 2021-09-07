package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * MovieRental entity which keeps movie rental details
 */
@Data
@Builder
@AllArgsConstructor
public class MovieRental {

    private String movieId;
    private int days;

}
