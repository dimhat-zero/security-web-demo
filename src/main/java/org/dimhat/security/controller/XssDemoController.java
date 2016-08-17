package org.dimhat.security.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xss")
public class XssDemoController {

	private static final Logger logger=Logger.getLogger(XssDemoController.class);

	private String commit="";
	private Boolean safe=false;

	@RequestMapping("article")
	public String article(Model model){
		model.addAttribute("commit",commit);
		model.addAttribute("safe",safe);
		return "xss";
	}

	/**
	 * 存取评论到数据库
	 * 这里为了简单存到属性中(并发会出错）
	 */
	@RequestMapping("commit")
	public String commit(Model model,String commit,Boolean safe){
		this.commit=commit;
		this.safe=safe;
		return "redirect:/xss/article";
	}
}
