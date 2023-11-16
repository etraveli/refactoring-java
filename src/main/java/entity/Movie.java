package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Long movieId;
    private String title;
    private MovieType movieType;


    public double getRentAmount(int rentalDays) {
        return movieType.getRentAmount(rentalDays);
    }
    public int getFrequentEnterPoints(int rentalDays) {
        return movieType.getFrequentEnterPoints(rentalDays);
    }
}
