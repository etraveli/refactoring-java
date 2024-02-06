package com.movie.rental.customexception;

@SuppressWarnings("serial")
public class InvalidCustomerException extends RuntimeException  {

	public InvalidCustomerException(String msg) {
		super(msg);
	}
}
