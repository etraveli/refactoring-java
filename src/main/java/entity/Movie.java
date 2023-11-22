package entity;

public class Movie {
    private final Id movieId;
    private final String title;
    private final Category category;

    public enum Category {
        REGULAR, CHILDREN, NEW
    }

    public enum Id {
        F001, F002, F003, F004
    }

    public Movie(Id movieId, String title, Category code) {
        this.movieId = movieId;
        this.title = title;
        this.category = code;
    }

    public Id getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }
}
