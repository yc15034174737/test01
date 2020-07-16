package com.dj.ssm.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/")
public class IndexPageController {

	@RequestMapping("toIndex")
	public String toIndex(String token, Model model) {
		model.addAttribute("token", token);
		return "index/index";
	}

	@RequestMapping("toLeft")
	public String toLeft(String token, Model model) {
		model.addAttribute("token", token);
		return "index/left";
	}

	@RequestMapping("toRight")
	public String toRight(String token, Model model) {
		model.addAttribute("token", token);
		return "index/right";
	}

	@RequestMapping("toTop")
	public String toTop(String token, Model model) {
		model.addAttribute("token", token);
		return "index/top";
	}

}
