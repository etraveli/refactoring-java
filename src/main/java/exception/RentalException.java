package exception;

public class RentalException extends RuntimeException {
    public RentalException() {
        super();
    }
    public RentalException(ExceptionCode code){
        super(code.getKey() + code.getValue());
    }
    public RentalException(ExceptionCode code,String message) {
        super(code.getKey() + code.getValue() + message);
    }
    public RentalException(ExceptionCode code, String message, Throwable cause) {
        super(code.getKey() + code.getValue() + message, cause);
    }
    public RentalException(Throwable cause) {
        super(cause);
    }
}
