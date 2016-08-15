package org.dimhat.security.exception;

public class PasswordIncorrectException extends ServiceException {

	public PasswordIncorrectException() {
		super();
	}

	public PasswordIncorrectException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordIncorrectException(String message) {
		super(message);
	}

}
