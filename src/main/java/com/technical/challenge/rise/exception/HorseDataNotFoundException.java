package com.technical.challenge.rise.exception;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class HorseDataNotFoundException extends RuntimeException{
	HorseDataNotFoundException(String message){
		super(message);
	}

}
