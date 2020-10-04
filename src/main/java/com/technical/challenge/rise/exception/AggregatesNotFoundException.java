package com.technical.challenge.rise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AggregatesNotFoundException extends RuntimeException{
	
	public AggregatesNotFoundException(String message){
		super(message);
	}

}
