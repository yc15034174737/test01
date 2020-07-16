package com.dj.ssm.controller.page;


import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserPageController {

    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin(String token, Model model) {
        model.addAttribute("token", token);
        return "user/login";
    }

    @RequestMapping("out")
    public String out() {
        return "user/login";
    }

    @RequestMapping("toShow")
    public String toShow(String token, Model model) {
        model.addAttribute("token", token);
        return "user/show";
    }

    @RequestMapping("toAdd")
    public String toAdd(String token, Model model) {
        model.addAttribute("token", token);
        return "user/add";
    }
    @RequestMapping("findUserById")
    public String findUserById(String token, Integer id, Model model) throws Exception {
        User user = userService.findUserById(id);
        model.addAttribute("token", token);
        model.addAttribute("user", user);
        return "user/update";
    }

}
