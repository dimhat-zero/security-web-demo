package org.dimhat.security.web;

import org.apache.log4j.Logger;
import org.dimhat.security.exception.ServiceException;
import org.dimhat.security.exception.UserNotLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;


/**
 * 通用异常处理
 * 应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行
 * 
 * 配置在spring-mvc.xml中，没有跟controller一起扫描到
 * 
 * @author dimhat
 * @date 2015年12月4日 下午2:02:04
 * @version 1.0
 */
@ControllerAdvice
public class DefaultExceptionHandler {

	private Logger logger=Logger.getLogger(DefaultExceptionHandler.class);
	
	@ExceptionHandler(UserNotLoginException.class)
	public String userNotLogin(NativeWebRequest request, UserNotLoginException e) {
		logger.error("用户没有登录异常：" + e.getMessage());
		return "redirect:/login";
	}
	
	@ExceptionHandler({ ServiceException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView jianlaException(NativeWebRequest request, ServiceException e) {
		logger.debug("发生业务异常");
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.setViewName("page");
		return mv;
	}
	
	//@ExceptionHandler({NullPointerException.class})
	public ModelAndView nllPointerException(NativeWebRequest request, NullPointerException e){
		logger.debug("空指针异常");
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.setViewName("unauthorized");
		return mv;
	} 
}
