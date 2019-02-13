package com.remion;

public class BoothConnectionException extends RuntimeException {
	public BoothConnectionException(String message) {
		super(message);
	}

	public BoothConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
