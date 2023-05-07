package se.etraveli.movie.rentals.business.bonus;

import org.springframework.stereotype.Component;
import se.etraveli.movie.rentals.model.MovieCode;

@Component
public class FrequentEnterPointsImpl implements FrequentEnterPoints {

    @Override
    public int getFrequentEnterPoints(MovieCode movieCode, int days) {
        return movieCode == MovieCode.NEW && days > 2 ? 2 : 1;
    }
}
