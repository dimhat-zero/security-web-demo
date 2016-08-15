package org.dimhat.security.controller;

import org.apache.log4j.Logger;
import org.dimhat.security.entity.User;
import org.dimhat.security.model.UserInfoModel;
import org.dimhat.security.model.UserUpdateForm;
import org.dimhat.security.service.UserService;
import org.dimhat.security.web.annotation.Token;
import org.dimhat.security.web.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("user")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(@UserInfo() UserInfoModel userInfo, Model model) {
		logger.debug(JSON.toJSONString(userInfo));
		return "user/home";
	}

    //使用token
    @RequestMapping(value = "update", method = RequestMethod.GET)
    @Token(create = true)
	public String userInfoUpdate(@UserInfo() UserInfoModel userInfo, Model model) {
		logger.debug(JSON.toJSONString(userInfo));
		User user = userService.getUserById(userInfo.getId());
		model.addAttribute("user", user);
        return "user/info";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @Token(remove = true)
	public String doUserInfoUpdate(@UserInfo() UserInfoModel userInfo, UserUpdateForm form, RedirectAttributes ra) {
		form.setId(userInfo.getId());
		logger.debug("修改用户信息：" + JSON.toJSONString(form));
		userService.update(form);
		ra.addFlashAttribute("msg", "修改用户信息成功");
		ra.addFlashAttribute("code", 1);
		return "redirect:/user/update";
    }

}
