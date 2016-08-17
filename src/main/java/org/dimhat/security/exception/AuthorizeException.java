package org.dimhat.security.exception;

/**
 * Created by think on 2016/8/17.
 */
public class AuthorizeException extends ServiceException {

    public AuthorizeException() {
    }

    public AuthorizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizeException(String message) {
        super(message);
    }
}
