package org.dimhat.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xss")
public class XssDemoController {

	@RequestMapping("")
	public String xss(Model model){
		model.addAttribute("", "");
		return "xss";
	}
}
