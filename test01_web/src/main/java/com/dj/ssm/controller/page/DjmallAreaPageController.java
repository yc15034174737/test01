package com.dj.ssm.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/djmallArea/")
@Controller
public class DjmallAreaPageController {

    @RequestMapping("toShow")
    public String toShow(String token, Model model) {
        model.addAttribute("token", token);
        return "djmallArea/show";
    }

    @RequestMapping("toShowSelect")
    public String toShowSelect(String token, Model model) {
        model.addAttribute("token", token);
        return "djmallArea/showSelect";
    }

}
