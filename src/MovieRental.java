public class MovieRental {
    // now considering the actual Movie
    private Movie movie;
    // "rentalDays" is more descriptive than "days"
    private int rentalDays;

    public MovieRental(Movie movie, int rentalDays) {
        this.movie = movie;
        this.rentalDays = rentalDays;
    }

    // in case the Movie needs to be changed/updated
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    // in case the number of rental days needs to be changed/updated
    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getRentalDays() {
        return rentalDays;
    }
}
