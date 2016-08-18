package org.dimhat.security.exception;

public class UserNotLoginException extends ServiceException {

	private String redirectUrl;

	public UserNotLoginException() {
		super();
		setCode(ExceptionCode.NOT_LOGIN_CODE);
	}

	public UserNotLoginException(String redirectUrl){
		super();
		this.redirectUrl=redirectUrl;
		setCode(ExceptionCode.NOT_LOGIN_CODE);
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}
}
