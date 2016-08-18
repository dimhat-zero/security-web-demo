package org.dimhat.security.exception;

/**
 * token过期异常
 */
public class TokenExpiredException extends ServiceException {

    public TokenExpiredException() {
    }

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
