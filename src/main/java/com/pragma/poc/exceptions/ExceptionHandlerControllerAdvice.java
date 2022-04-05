package com.pragma.poc.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException exception) {
		ErrorObject error = new ErrorObject();
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleClientAlreadyExists(ClientAlreadyExistsException exception) {
		ErrorObject error = new ErrorObject();
		error.setStatusCode(HttpStatus.CONFLICT.value());
		error.setMessage(exception.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleNoDataFoundException(NoDataFoundException exception) {
		ErrorObject error = new ErrorObject();
		error.setStatusCode(HttpStatus.NO_CONTENT.value());
		error.setMessage(exception.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(error, HttpStatus.NO_CONTENT);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleClientServiceException(ClientServiceException exception) {
		ErrorObject error = new ErrorObject();
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exception.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleBadRequestException(BadRequestException exception) {
		ErrorObject error = new ErrorObject();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exception.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(error, HttpStatus.BAD_REQUEST);
	}
}
