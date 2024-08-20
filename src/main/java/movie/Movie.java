package main.java.movie;

public class Movie {
    private String title;
    private MovieGenre code;

    public Movie(String title, MovieGenre code) {

        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public MovieGenre getCode() {
        return code;
    }
}
