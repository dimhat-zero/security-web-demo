package org.dimhat.security.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dimhat.security.annotation.Token;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <pre>
 * token 目的：
 * 	CSRF，重复表单提交
 * 
 * token 验证规则：
 * 	进入表单页面时创建token
 * 	提交表单时消耗token
 * </pre>
 * @author dimhat
 * @date 2016年8月12日 下午2:50:37
 * @version 1.0
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(TokenInterceptor.class);

	/** 
	 * 解决 重复提交的表单 和 CSRF
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {//处理请求的是方法
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class);
			if (annotation != null) {
				//create
				if (annotation.create()) {
					request.getSession(true).setAttribute("token", UUID.randomUUID().toString());
				}
				//remove
				if (annotation.remove()) {
					if (!checkToken(request)) {//拒绝请求
						logger.warn("Request be refused with incorrect token verify, Url:" + request.getServletPath());
						return false;
					}
					request.getSession(true).removeAttribute("token");
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * token相同返回true
	 * @param request
	 * @return  
	 */
	private boolean checkToken(HttpServletRequest request) {
		String serverToken = (String) request.getSession(true).getAttribute("token");
		if (serverToken == null) {
			return false;
		}
		String clinetToken = request.getParameter("token");
		if (clinetToken == null) {
			return false;
		}
		if (!serverToken.equals(clinetToken)) {
			return false;
		}
		return true;
	}

}
