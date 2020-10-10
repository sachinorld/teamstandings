package com.football.standings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.football.standings.model.BaseErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class StandingsExceptionHandler {

	@ExceptionHandler({ApplicationException.class})
	public ResponseEntity<BaseErrorResponse> handleApplicationException(Exception e) {
		BaseErrorResponse error = new BaseErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST);
		error.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<BaseErrorResponse> handleException(Exception e) {
		BaseErrorResponse error = new BaseErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
