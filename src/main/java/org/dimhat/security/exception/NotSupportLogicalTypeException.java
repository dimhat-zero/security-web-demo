package org.dimhat.security.exception;

/**
 * 不支持的逻辑类型异常
 */
public class NotSupportLogicalTypeException extends ServiceException{

    public NotSupportLogicalTypeException() {
    }

    public NotSupportLogicalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportLogicalTypeException(String message) {
        super(message);
    }
}
