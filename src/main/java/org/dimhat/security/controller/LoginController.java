package org.dimhat.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dimhat.security.Constant;
import org.dimhat.security.entity.User;
import org.dimhat.security.exception.AccountNotFindException;
import org.dimhat.security.exception.PasswordIncorrectException;
import org.dimhat.security.model.UserInfoModel;
import org.dimhat.security.service.AuthorizeService;
import org.dimhat.security.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
    @Autowired
    private AuthorizeService authorizeService;

	//使用验真码
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		if (needVeriCode(request)) {
			model.addAttribute("veriCode", true);
		}
		return "user/login";
	}

	@RequestMapping(value="logout",method=RequestMethod.POST)
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(Constant.userInfo);
		return "redirect:/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam(value = "redirect_url", required = false, defaultValue = "user/home") String redirectUrl,
			HttpServletRequest request, RedirectAttributes ra, String veriCode) {
		//如果频繁登录错误则需要验证码
		if (needVeriCode(request)) {
			logger.debug("【登录请求需要验证码】");
			HttpSession session = request.getSession(true);
			String sessionVeriCode = (String) session.getAttribute(Constant.veriCode);
			if (veriCode != null && veriCode.toLowerCase().equals(sessionVeriCode)) {
				logger.debug("验证码通过");
				session.removeAttribute(Constant.veriCode);
			} else {
				logger.debug("验证码未通过");
				ra.addFlashAttribute("msg", "验证码不正确");
				ra.addFlashAttribute("code", 1);
				return "redirect:login";
			}
		}

		//登录
		User loginForm = new User();
		loginForm.setUsername(username);
		loginForm.setPassword(password);

		User user = null;
		try {
			user = userService.login(loginForm);
		} catch (AccountNotFindException ane) {
			ra.addFlashAttribute("msg", "找不到用户名");
			ra.addFlashAttribute("code", 1);
			loginTimePlus(request);
			return "redirect:login";
		} catch (PasswordIncorrectException pie) {
			ra.addFlashAttribute("msg", "密码不正确");
			ra.addFlashAttribute("code", 2);
			loginTimePlus(request);
			return "redirect:login";
		}

		UserInfoModel userInfo = buildUserInfo(user);
		logger.debug("用户：" + JSON.toJSONString(userInfo) + "登录成功");

		HttpSession session = request.getSession(true);
		session.setAttribute(Constant.userInfo, userInfo);
		loginTimeRemove(request);
		//跳转
		return "redirect:" + redirectUrl;

	}

	private UserInfoModel buildUserInfo(User user) {
		UserInfoModel userInfo = new UserInfoModel();
        //test
        Set<String> role = new HashSet<>();
        role.add("admin");
        userInfo.setRoles(role);
        userInfo.setPerms(role);
        //userInfo.setRoles(authorizeService.findRoles(user.getId()));
        //userInfo.setPerms(authorizeService.findPerms(user.getId()));
		BeanUtils.copyProperties(user, userInfo);
		return userInfo;
	}

	/**
	 * 扩展：记录登录错误次数
	 * 方法1：放在session中，无法防止删除cookie
	 * 方法2：放在redis中 限制10分钟过期
	 * 为了简单，这里使用第一种方式
	 */
	boolean needVeriCode(HttpServletRequest request) {//判断是否要验证码
		HttpSession session = request.getSession(true);
		Integer loginTime = (Integer) session.getAttribute(Constant.loginTime);
		logger.debug("current login error times [" + loginTime + "] ; max error times[" + Constant.limitLoginTime
				+ "] ");
		if (loginTime == null || loginTime <= Constant.limitLoginTime) {
			return false;
		}
		logger.warn("login error times is to mush,it will have vericode");
		return true;
	}

	void loginTimePlus(HttpServletRequest request) {//登录出错次数+1
		HttpSession session = request.getSession(true);
		Integer loginTime = (Integer) session.getAttribute(Constant.loginTime);
		if (loginTime == null)
			loginTime = 0;
		session.setAttribute(Constant.loginTime, loginTime + 1);
		logger.debug("login time plus 1");
	}

	void loginTimeRemove(HttpServletRequest request) {//移除出错请求
		HttpSession session = request.getSession(true);
		session.removeAttribute(Constant.loginTime);
		logger.debug("login time remove");
	}

}
