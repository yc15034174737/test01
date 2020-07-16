package com.dj.ssm.controller;

import com.dj.ssm.pojo.Product;
import com.dj.ssm.pojo.ResultModel;
import com.dj.ssm.pojo.Token;
import com.dj.ssm.service.ProductService;
import com.dj.ssm.service.TokenService;
import com.dj.ssm.utils.SysConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping("show")
    public ResultModel<Object> show(Integer pageNo) {
        Map<String, Object> map = new HashMap<>();
        try {
            PageHelper.startPage(pageNo, SysConstant.PAGE_SIZE);
            List<Product> list = productService.findAll(SysConstant.IS_DEL_1);
            PageInfo<Product> pageInfo = new PageInfo<Product>(list);
            map.put("pages", pageInfo.getPages());
            map.put("list", pageInfo.getList());
            return new ResultModel<Object>().success(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常");
        }
    }

    @RequestMapping("addProduct")
    public ResultModel<Object> addProduct(Product product, String token) {
        try {
            Token selToken = tokenService.findTokenByUserToken(token);
            product.setUserId(selToken.getUserId());
            productService.addProduct(product);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常");
        }
    }

    @RequestMapping("updateProduct")
    public ResultModel<Object> updateProduct(Product product, String token) {
        try {
            Token selToken = tokenService.findTokenByUserToken(token);
            product.setUserId(selToken.getUserId());
            Product disProduct = productService.findProductByProName(product.getProName());
            if(null != disProduct && disProduct.getId() != product.getId()){
                return new ResultModel<Object>().error("用户名已重复");
            }
            productService.updateProduct(product);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常");
        }
    }


    @RequestMapping("updateProductIsDel")
    public ResultModel<Object> updateProductIsDel(Integer id) {
        try {
            productService.updateProduct(new Product().setId(id).setIsDel(SysConstant.IS_DEL_0));
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常");
        }
    }

    @RequestMapping("findProductByProName")
    public Boolean findProductByProName(String proName) {
        try {
            Product product = productService.findProductByProName(proName);
            return product == null ? true : false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }


    }

}
