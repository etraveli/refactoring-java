package movie.service.amount;

import movie.dto.MovieRental;

public interface MovieRentalAmountService {

    double getAmountForMovieRental(MovieRental movieRental);
}
