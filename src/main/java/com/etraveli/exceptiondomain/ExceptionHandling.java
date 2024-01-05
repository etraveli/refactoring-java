package com.etraveli.exceptiondomain;

import com.etraveli.exceptiondomain.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<HttpResponse> dataNotFoundException(DataNotFoundException e) {
        return this.createHttpResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }
    @ExceptionHandler(UnexpectedResultException.class)
    public ResponseEntity<HttpResponse> unexpectedResultException(UnexpectedResultException e) {
        return this.createHttpResponse(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
    }
    @ExceptionHandler(MovieIllegalArgumentException.class)
    public ResponseEntity<HttpResponse> movieIllegalArgumentException(MovieIllegalArgumentException e) {
        return this.createHttpResponse(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
    }
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<HttpResponse> alreadyExistException(AlreadyExistException e) {
        return this.createHttpResponse(HttpStatus.CONFLICT, e.getMessage());
    }
}
