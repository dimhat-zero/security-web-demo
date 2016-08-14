package org.dimhat.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    //使用验真码
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {

        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin(String redirectUrl) {
        if (StringUtils.hasLength(redirectUrl)) {
            return "redirect:" + redirectUrl;
        }
        return "user/home";
    }
}
