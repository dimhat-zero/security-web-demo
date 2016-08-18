package org.dimhat.security.exception;

/**
 * 服务层异常
 * @author dimhat
 * @date 2016年8月13日 下午5:51:00
 * @version 1.0
 */
public class ServiceException extends RuntimeException {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

}
