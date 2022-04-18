package refactoring.movie;

import java.util.Objects;

public abstract class Movie {

    private final String title;

    public Movie(String title) {
        this.title = Objects.requireNonNull(title);
    }

    public String getTitle() {
        return title;
    }

    public abstract double getRentalAmount(int daysRented);

    public int getFrequencyPoints(int daysRented) {
        return 1;
    }
}
