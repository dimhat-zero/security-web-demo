package org.dimhat.security.web;

import org.apache.log4j.Logger;
import org.dimhat.security.exception.ServiceException;
import org.dimhat.security.exception.TokenExpiredException;
import org.dimhat.security.exception.UnauthorizeException;
import org.dimhat.security.exception.UserNotLoginException;
import org.dimhat.security.util.AjaxUtil;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


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

	/**
	 * 处理未登录的异常
	 * json -- success=false
	 * html -- login页面
	 */
	@ExceptionHandler(UserNotLoginException.class)
	public ModelAndView userNotLogin(HttpServletRequest request, UserNotLoginException e) {
		logger.error("用户没有登录异常");
		ModelAndView mav=new ModelAndView();
		if(AjaxUtil.isAjaxRequest(request)){
			//ajax请求，返回json视图
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map attributes = new HashMap();
			attributes.put("success",false);
			attributes.put("msg","用户没有登录");
			attributes.put("code",e.getCode());
			view.setAttributesMap(attributes);
			mav.setView(view);
		}else{
			String redirectUrl = e.getRedirectUrl();
			String loginUrl="redirect:/login";
			if(redirectUrl!=null){
				loginUrl+="?redirect_url="+redirectUrl;
			}
			mav.setViewName(loginUrl);
		}
		return mav;
	}

	/**
	 * 处理未授权的异常
	 * json -- success=false
	 * html -- unauthorize页面
	 */
	@ExceptionHandler(UnauthorizeException.class)
	public ModelAndView unauthorize(HttpServletRequest request,
									HttpServletResponse response, UnauthorizeException e){
		String requestUri = request.getRequestURI();
		logger.debug("未授权异常请求路径["+requestUri+"]");
		logger.error(e.getMessage());
		ModelAndView mav=new ModelAndView();
		if(AjaxUtil.isAjaxRequest(request)){
			//ajax请求，返回json视图
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map attributes = new HashMap();
			attributes.put("success",false);
			attributes.put("msg",e.getMessage());
			attributes.put("code",e.getCode());
			view.setAttributesMap(attributes);
			mav.setView(view);
		}else{
			mav.setViewName("unauthorize");
			mav.addObject("msg",e.getMessage());
		}
		return mav;
	}

	@ExceptionHandler(TokenExpiredException.class)
	public ModelAndView tokenExpire(HttpServletRequest request,TokenExpiredException e){
		logger.error("token过期异常");

		ModelAndView mav=new ModelAndView();
		if(AjaxUtil.isAjaxRequest(request)){
			//ajax请求，返回json视图
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map attributes = new HashMap();
			attributes.put("success",false);
			attributes.put("msg","token过期");
			attributes.put("code",e.getCode());
			view.setAttributesMap(attributes);
			mav.setView(view);
		}else{
			mav.setViewName("exception");
			mav.addObject("msg",e.getMessage());
		}
		return mav;
	}
	
	//@ExceptionHandler({ ServiceException.class })
	//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView jianlaException(NativeWebRequest request, ServiceException e) {
		logger.error("发生业务异常",e);
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage());
		mv.setViewName("exception");
		return mv;
	}
	
	//@ExceptionHandler({Exception.class})
	public ModelAndView nullPointerException(HttpServletRequest request, Exception e){
		logger.error("发生未知异常",e);
		ModelAndView mav=new ModelAndView();
		if(AjaxUtil.isAjaxRequest(request)){
			//ajax请求，返回json视图
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map attributes = new HashMap();
			attributes.put("success",false);
			attributes.put("msg",e.getMessage());
			view.setAttributesMap(attributes);
			mav.setView(view);
		}else{
			mav.setViewName("exception");
			mav.addObject("msg",e.getMessage());
		}
		return mav;
	}

}
