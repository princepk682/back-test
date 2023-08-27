package com.y2tek.backtest.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BackTestingException extends Exception {

	private static final long serialVersionUID = -1827658069161795706L;

	private HttpStatus statusCode;

	public BackTestingException() {
		super();
	}

	public BackTestingException(HttpStatus statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}
}
