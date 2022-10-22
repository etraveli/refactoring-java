package movie;

import movie.code.MovieCode;
import movie.code.exceptions.MovieCodeNotFoundException;
import movie.code.exceptions.MovieCodeInstantiationException;

public class Movie {
    private String title;
    private MovieCode code;

    public Movie(String title, String code) throws MovieCodeNotFoundException,
            MovieCodeInstantiationException {
        this.title = title;
        this.code = MovieCode.build(code);
    }

    public String getTitle() {
        return title;
    }

    public MovieCode getCode() {
        return code;
    }

    public double calculateAmount(int days) {
        return code.calculateAmount(days);
    }

    public boolean hasExtraBonusPoint(int days) {
        return code.hasExtraBonusPoint(days);
    }
}
