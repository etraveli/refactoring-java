package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRental {
    private final Long movieRentalId;
    private String movieCode;
    private int days;
    private Movie movie;


    public MovieRental(Long movieRentalId,String movieCode, int days,Movie movie) {
        this.movieRentalId=movieRentalId;
        this.movieCode = movieCode;
        this.days = days;
        this.movie = movie;
    }

    public double getRentAmount() {
        return movie.getRentAmount(days);
    }

    public int getFrequentEnterPoints() {
        return movie.getFrequentEnterPoints(days);
    }



}
