/**
 * Rent for regular movie is 2 and 1.5 extra for the third day and so on.
 * Rent for New movies is 3 per day.
 * Rent for Children movie is 1.5 per day and 1.5 extra for the fourth day and so on.
 */

public enum MovieType {
    REGULAR(2) {
        @Override
        public double getTotalRent(MovieRental rentedMovie) {
            return ((rentedMovie.getMovieRentedDays() > regularMovieLimit) ? ((rentedMovie.getMovieRentedDays() - regularMovieLimit) * AdditionalRent) + getRent() : getRent());
        }
    },
    NEW(3) {
        @Override
        public double getTotalRent(MovieRental rentedMovie) {
            return (rentedMovie.getMovieRentedDays() * getRent());
        }
    },
    CHILDRENS(1.5) {
        @Override
        public double getTotalRent(MovieRental rentedMovie) {
            return ((rentedMovie.getMovieRentedDays() > newMovieLimit) ? ((rentedMovie.getMovieRentedDays() - newMovieLimit) * AdditionalRent) + getRent() : getRent());
        }
    };

    double AdditionalRent = 1.5;
    int regularMovieLimit = 2;
    int newMovieLimit = 3;

    private double rent;

    MovieType(double rent) {
        this.rent = rent;
    }

    public abstract double getTotalRent(MovieRental rentedMovie);

    protected double getRent() {
        return rent;
    }
}
