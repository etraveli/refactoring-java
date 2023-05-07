package se.etraveli.movie.rentals.business.bonus;

import org.springframework.stereotype.Service;
import se.etraveli.movie.rentals.model.MovieCode;

@Service
public class FrequentEnterPointsImpl implements FrequentEnterPoints {

    @Override
    public int getFrequentEnterPoints(MovieCode movieCode, int days) {
        return movieCode == MovieCode.NEW && days > 2 ? 2 : 1;
    }
}
