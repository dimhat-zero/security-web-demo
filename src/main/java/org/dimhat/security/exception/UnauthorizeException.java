package org.dimhat.security.exception;

/**
 * Created by think on 2016/8/18.
 */
public class UnauthorizeException  extends ServiceException{
    public UnauthorizeException() {
        setCode(ExceptionCode.NOT_AUTHORIZE_CODE);
    }

    public UnauthorizeException(String message, Throwable cause) {
        super(message, cause);
        setCode(ExceptionCode.NOT_AUTHORIZE_CODE);
    }

    public UnauthorizeException(String message) {
        super(message);
        setCode(ExceptionCode.NOT_AUTHORIZE_CODE);
    }
}
