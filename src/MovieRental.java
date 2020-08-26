public class MovieRental {
    // now considering the actual Movie "id"
    private int movieId;
    // "rentalDays" is more descriptive than "days"
    private int rentalDays;

    public MovieRental(int movieId, int rentalDays) {
        this.movieId = movieId;
        this.rentalDays = rentalDays;
    }

    // in case the Movie id needs to be changed/updated
    public void setMovieId(int id) {
        this.movieId = id;
    }

    public int getMovieId() {
        return movieId;
    }

    // in case the number of rental days needs to be changed/updated
    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getRentalDays() {
        return rentalDays;
    }
}
