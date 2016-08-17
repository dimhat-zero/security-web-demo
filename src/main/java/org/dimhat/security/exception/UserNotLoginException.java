package org.dimhat.security.exception;

public class UserNotLoginException extends ServiceException {

	private String redirectUrl;

	public UserNotLoginException() {
		super();
	}

	public UserNotLoginException(String redirectUrl){
		super();
		this.redirectUrl=redirectUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}
}
