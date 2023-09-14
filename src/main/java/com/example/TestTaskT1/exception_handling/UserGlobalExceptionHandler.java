package com.example.TestTaskT1.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<IncorrectData> handleException(WrongLineException exception){
		IncorrectData data = new IncorrectData();
		data.setInfo(exception.getMessage());
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}

}
