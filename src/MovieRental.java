//TODO lombok
public class MovieRental {
    private Movie movie;

    private int totalRentalDays;

    public MovieRental(Movie movie, int totalRentalDays) {
        this.movie = movie;
        this.totalRentalDays = totalRentalDays;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getTotalRentalDays() {
        return totalRentalDays;
    }


}
