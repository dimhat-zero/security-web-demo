package org.dimhat.security.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dimhat.security.exception.ExceptionCode;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class InitBinderHandler {

	/* 处理页面时间参数为空时的转换 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ModelAttribute
	public void exceptionCode(Model model){
		model.addAttribute("exceptionCode", ExceptionCode.getInstance());
	}
}
