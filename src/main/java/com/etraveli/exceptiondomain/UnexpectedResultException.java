package com.etraveli.exceptiondomain;

public class UnexpectedResultException extends RuntimeException {
    public UnexpectedResultException(String unexpectedResult) {
        super(unexpectedResult);
    }
}
