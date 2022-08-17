package exception;

/**
 * @class MovieNotFoundException
 * 
 * MISSION: exception thrown when the customer is not provided.
 */
@SuppressWarnings("serial")
public class MovieNotFoundException extends RuntimeException {
	
	private final static String ERROR = "Movie not found.";
	
	/*
	 * CONSTRUCTOR
	 */
	public MovieNotFoundException( String message ) {
		super( message );
	}
	public MovieNotFoundException() {
		this( ERROR );
	}

}