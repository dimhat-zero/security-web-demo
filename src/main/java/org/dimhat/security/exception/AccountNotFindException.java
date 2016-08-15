package org.dimhat.security.exception;

public class AccountNotFindException extends ServiceException {

	public AccountNotFindException() {
		super();
	}

	public AccountNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountNotFindException(String message) {
		super(message);
	}

}
