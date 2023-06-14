package io.refactoring.model;

public enum MovieCode {
    REGULAR("regular"),
    NEW("new"),
    CHILDREN("childrens");

    public final String genre;

    MovieCode(String genre) {
        this.genre = genre;
    }

    public static MovieCode valueOfGenre(String genre) {
        for (MovieCode code : values()) {
            if (code.genre.equals(genre)) {
                return code;
            }
        }
        return null;
    }
}
