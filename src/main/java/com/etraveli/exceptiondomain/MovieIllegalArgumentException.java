package com.etraveli.exceptiondomain;

public class MovieIllegalArgumentException extends RuntimeException {
    public MovieIllegalArgumentException(String message) {
        super(message);
    }
}
