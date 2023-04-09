package com.etraveliGroup.exception;

public class InvalidMoveIdException extends RuntimeException {
    public InvalidMoveIdException() {}

    public InvalidMoveIdException(String msg)
    {
        super(msg);
    }
}
