import interfaces.MovieRentalCalculator;
import models.MovieRental;
import models.enums.MovieCode;

//Strategy to calculate the frequent enter points
public class FrequentEnterPointsCalculator implements MovieRentalCalculator<MovieRental, Integer> {

    private MovieLibrary movieLibrary = Provider.getInstance().getMovieLibrary();
    @Override
    public Integer calculateFromMovieRental(MovieRental movieRental) {
        int frequentEnterPoints = 1;
        // add bonus for a two day new release rental
        if (movieLibrary.getMovieById(movieRental.getMovieId()).getCode() == MovieCode.NEW && movieRental.getDays() > 2)
            frequentEnterPoints++;
        return frequentEnterPoints;
    }
}
