package org.dimhat.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dimhat.security.Constant;
import org.dimhat.security.entity.User;
import org.dimhat.security.exception.UsernameExistException;
import org.dimhat.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private UserService userService;

	//使用验真码
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {
		return "user/register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String doRegister(String username, String password, String veriCode, HttpServletRequest request,
			RedirectAttributes ra) {
		HttpSession session = request.getSession(true);
		//校验验证码
		String sessionVeriCode = (String) session.getAttribute(Constant.veriCode);
		logger.debug("comp session vericode [" + sessionVeriCode + "] if equal form submit vericode [" + veriCode + "]");
		if (veriCode != null && veriCode.toLowerCase().equals(sessionVeriCode)) {
			//验证码通过后移除，防止重复提交
			logger.debug("验证码通过");
			session.removeAttribute(Constant.veriCode);
			//注册用户
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			try {
				userService.register(user);
			} catch (UsernameExistException uee) {
				ra.addFlashAttribute("msg", "用户名已经存在");
				ra.addFlashAttribute("code", 1);
				return "redirect:register";
			} catch (Exception e) {
				ra.addFlashAttribute("msg", "未知异常：" + e.getMessage());
				ra.addFlashAttribute("code", 0);
				return "redirect:register";
			}

			logger.debug("注册用户成功！用户名[" + username + "]");
			return "redirect:login";
		} else {
			logger.debug("验证码未通过");
			ra.addFlashAttribute("code", 1);
			ra.addFlashAttribute("msg", "验证码不正确");
			return "redirect:register";
		}
	}

}
