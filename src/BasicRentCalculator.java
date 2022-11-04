import interfaces.MovieRentalCalculator;
import models.MovieRental;
import models.enums.MovieCode;

public class BasicRentCalculator implements MovieRentalCalculator<MovieRental, Double> {

    private MovieLibrary movieLibrary = new MovieLibrary();

    @Override
    public Double calculateFromMovieRental(MovieRental movieRental) {
        double thisAmount = 0;
        if (movieLibrary.getMovieById(movieRental.getMovieId()).getCode().equals(MovieCode.REGULAR)) {
            thisAmount = 2;
            if (movieRental.getDays() > 2) {
                thisAmount = ((movieRental.getDays() - 2) * 1.5) + thisAmount;
            }
        }
        if (movieLibrary.getMovieById(movieRental.getMovieId()).getCode().equals(MovieCode.NEW)) {
            thisAmount = movieRental.getDays() * 3;
        }
        if (movieLibrary.getMovieById(movieRental.getMovieId()).getCode().equals(MovieCode.CHILDREN)) {
            thisAmount = 1.5;
            if (movieRental.getDays() > 3) {
                thisAmount = ((movieRental.getDays() - 3) * 1.5) + thisAmount;
            }
        }
        return thisAmount;
    }
}
