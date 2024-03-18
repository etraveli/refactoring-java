//TODO lombok
public class Movie {
    private int id;
    private String code;
    private String title;
    private MovieType movieType;

    public Movie(int id, String code, String title, MovieType movieType) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.movieType = movieType;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }


    public MovieType getMovieType() {
        return movieType;
    }


}
