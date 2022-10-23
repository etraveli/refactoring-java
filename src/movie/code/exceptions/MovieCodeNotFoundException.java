package movie.code.exceptions;

public class MovieCodeNotFoundException extends Exception {
    public MovieCodeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
