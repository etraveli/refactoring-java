package com.etraveli.exceptiondomain;

public class RequestNotValidException extends RuntimeException {
    public RequestNotValidException(String unexpectedResult) {
        super(unexpectedResult);
    }
}
