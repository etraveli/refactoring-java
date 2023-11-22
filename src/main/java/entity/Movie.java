package entity;

public enum Movie {
    F001("You've Got Mail", Movie.Category.REGULAR),
    F002("Matrix", Movie.Category.REGULAR),
    F003("Cars", Movie.Category.CHILDREN),
    F004("Fast & Furious X", Movie.Category.NEW);

    private final String title;
    private final Movie.Category category;

    Movie(String title, Movie.Category category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public Movie.Category getCategory() {
        return category;
    }

    public enum Category {
        REGULAR, CHILDREN, NEW
    }
}
