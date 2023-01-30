public class MovieRental {
    private String movieId;
    private int days;

    public MovieRental(String movieId, int days) {
        this.movieId = movieId;
        this.days = days;
    }

    public String getMovieId() {
        return movieId;
    }

    public int getDays() {
        return days;
    }

    
/*
 * Added setters, even though they are not required in this scenario, in the bigger picture setters are required.
 */
    public void setMovieId(String movieId)
    {
        this.movieId = movieId;
    }

    public void setDays(int days)
    {
        this.days = days;
    }
    
}
