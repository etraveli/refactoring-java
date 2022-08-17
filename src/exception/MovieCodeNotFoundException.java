package exception;

/**
 * @class MovieCodeNotFoundException
 * 
 * MISSION: exception thrown when the movie code is not found.
 */
@SuppressWarnings("serial")
public class MovieCodeNotFoundException extends RuntimeException {
	
	private final static String ERROR = "Movie code is not found.";
	
	/*
	 * CONSTRUCTOR
	 */
	public MovieCodeNotFoundException( String message ) {
		super( message );
	}
	public MovieCodeNotFoundException() {
		this( ERROR );
	}

}
