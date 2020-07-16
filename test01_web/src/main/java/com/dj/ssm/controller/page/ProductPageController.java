package com.dj.ssm.controller.page;

import com.dj.ssm.pojo.Product;
import com.dj.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/")
public class ProductPageController {

    @Autowired
    private ProductService productService;

    @RequestMapping("toShow")
    public String toShow(String token, Model model) {
        model.addAttribute("token", token);
        return "product/show";
    }

    @RequestMapping("toAdd")
    public String toAdd(String token, Model model) {
        model.addAttribute("token", token);
        return "product/add";
    }
    @RequestMapping("findProductById")
    public String findProductById(String token, Integer id, Model model) throws Exception {
        Product product = productService.findProductById(id);
        model.addAttribute("token", token);
        model.addAttribute("product", product);
        return "product/update";
    }
}
