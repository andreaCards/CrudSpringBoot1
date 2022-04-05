package com.pragma.poc.exceptions;

public class ClientServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClientServiceException(final String message) {
		super(message);
	}

}
