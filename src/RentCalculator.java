import models.MovieRental;

public interface RentCalculator {
    double getTotalAmount(MovieRental movieRental);
}
