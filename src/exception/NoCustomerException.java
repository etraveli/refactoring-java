package exception;

/**
 * @class NoCustomerException
 * 
 * MISSION: exception thrown when the customer is not provided.
 */
@SuppressWarnings("serial")
public class NoCustomerException extends RuntimeException {
	
	private final static String ERROR = "Customer not provided.";
	
	/*
	 * CONSTRUCTOR
	 */
	public NoCustomerException( String message ) {
		super( message );
	}
	public NoCustomerException() {
		this( ERROR );
	}

}
