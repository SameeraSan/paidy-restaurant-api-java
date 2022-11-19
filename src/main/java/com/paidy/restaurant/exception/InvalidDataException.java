package com.paidy.restaurant.exception;

public class InvalidDataException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5032467318273950652L;

	public InvalidDataException (String message) {
		super(message);
	}

}
