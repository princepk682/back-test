package com.y2tek.backtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class BackTestingExceptionHandler {

	@ExceptionHandler(value = { BackTestingException.class })
	public ResponseEntity<Object> handleApiRequestException(BackTestingException apiException) {
		log.error(apiException.getMessage());
		return ResponseEntity.status(apiException.getStatusCode()).body(apiException.getMessage());
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleMethoArgumentNotValidException(MethodArgumentNotValidException apiException) {
		log.error(apiException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(apiException.getAllErrors().get(0).getDefaultMessage());
	}

	@ExceptionHandler(value = { AccessDeniedException.class })
	public final ResponseEntity<Object> commence(AccessDeniedException accessDeniedException) {
		log.error(accessDeniedException.getMessage());
		return new ResponseEntity<Object>(accessDeniedException.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = { ResourceAccessException.class })
	public final ResponseEntity<Object> handleTimeoutException(ResourceAccessException ex) {
		log.error(ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
	}

	@ExceptionHandler(value = { HttpClientErrorException.class })
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException apiException) {
		if (apiException.getRawStatusCode() == -9000) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException.getMessage());
		}
		log.error(apiException.getMessage());
		return ResponseEntity.status(apiException.getStatusCode()).body(apiException.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Object> handleAllExceptions(RuntimeException ex) {
		log.error("Run time exception to be printed ", ex);
		log.error(ex.getMessage());
		return new ResponseEntity<Object>("Something went wrong. Please try again later.",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
