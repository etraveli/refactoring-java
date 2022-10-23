package movie.rental;

public class MovieRental {
    private String customerName;
    private String movieId;
    private int days;

    public MovieRental(String customerName, String movieId, int days) {
        this.customerName = customerName;
        this.movieId = movieId;
        this.days = days;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMovieId() {
        return movieId;
    }

    public int getDays() {
        return days;
    }
}
