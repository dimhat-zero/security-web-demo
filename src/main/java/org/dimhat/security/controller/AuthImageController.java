package org.dimhat.security.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dimhat.security.Constant;
import org.dimhat.security.util.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthImageController {

	@RequestMapping("/veriCode")
	public void veriCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		//生成随机字串  
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		//存入会话session  
		HttpSession session = request.getSession(true);
		session.setAttribute(Constant.veriCode, verifyCode.toLowerCase());
		//生成图片  
		int w = 200, h = 80;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}
}
