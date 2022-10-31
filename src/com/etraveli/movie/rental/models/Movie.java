package com.etraveli.movie.rental.models;

public class Movie {

    private final String movieId;
    private final String title;
    private final MovieCode code;

    /**
     * A record that holds movie parameters.
     *
     * @param movieId id of the movie to make it unique as String
     * @param title   title of the movie as String
     * @param code    code o the movie as enum value
     */
    public Movie(final String movieId, final String title, final MovieCode code) {
        this.movieId = movieId;
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public MovieCode getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return movieId.equals(movie.movieId);
    }

    @Override
    public int hashCode() {
        return movieId.hashCode();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", code=" + code +
                '}';
    }

    /**
     * Enum of movie codes due to classify movies.
     */
    public enum MovieCode {
        REGULAR("regular"),
        CHILDREN("children"),
        NEW("new");

        private final String name;

        MovieCode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
