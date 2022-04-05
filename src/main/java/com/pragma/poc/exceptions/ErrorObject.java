package com.pragma.poc.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ErrorObject {
	private Integer statusCode;
	
	private String message;
	
	private long timestamp;
	
	

}
