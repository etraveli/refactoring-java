/**
 * The class holds information about movie title and the type of movie it is.
 * Type of movie are regular, new, children and other.
 * */
public class Movie {
    private String movieTitle;
    private MovieType movieType;

    public Movie(String title, MovieType code) {

        movieTitle = title;
        movieType = code;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public MovieType getMovieType() {
        return movieType;
    }
}
