package org.dimhat.security.exception;

public class UserNotLoginException extends ServiceException {

	public UserNotLoginException() {
		super();
	}

	public UserNotLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotLoginException(String message) {
		super(message);
	}

}
