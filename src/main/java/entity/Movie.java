package entity;

import enums.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Movie entity which keeps Movie details
 */
@Data
@AllArgsConstructor
@Builder
public class Movie {

    private String title;
    private MovieType movieType;
    private double amount;

}
