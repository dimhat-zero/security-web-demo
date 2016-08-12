package org.dimhat.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.dimhat.security.annotation.Token;
import org.dimhat.security.model.UserInfoUpdateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

	@RequestMapping(value = "update", method = RequestMethod.GET)
	@Token(create = true)
	public String userInfoUpdate(HttpServletRequest request) {

		return "user/info";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@Token(remove = true)
	public String doUserInfoUpdate(UserInfoUpdateForm form) {

		return "user/info";
	}
}