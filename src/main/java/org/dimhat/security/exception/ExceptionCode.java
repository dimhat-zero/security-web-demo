package org.dimhat.security.exception;

/**
 * 异常代码类
 * <pre>用于标识异常，没有错误代码是未知错误</pre>
 * 异常代码 |前两位系统号|中间三位模块号|最后三位异常号|
 * 系统01
 * 用户模块001
 */
public class ExceptionCode {
    //单例
    private static ExceptionCode instance = new ExceptionCode();

    public static ExceptionCode getInstance() {
        return instance;
    }

    public static final String NOT_LOGIN_CODE = "01001001";//未登录异常

    public static final String NOT_AUTHORIZE_CODE = "01001002";//未授权登录

    public String getNotLoginCode() {
        return NOT_LOGIN_CODE;
    }

    public String getNotAuthorizeCode() {
        return NOT_AUTHORIZE_CODE;
    }
}
