package interfaces;

import models.MovieRental;

public interface MovieRentalCalculator<T extends MovieRental,S>{
    S calculateFromMovieRental(T movieRental);
}
