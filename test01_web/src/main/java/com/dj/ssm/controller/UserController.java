package com.dj.ssm.controller;

import com.dj.ssm.pojo.ResultModel;
import com.dj.ssm.pojo.Token;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.TokenService;
import com.dj.ssm.service.UserService;
import com.dj.ssm.utils.QinniuUtil;
import com.dj.ssm.utils.QinniuUtil1;
import com.dj.ssm.utils.QinniuUtil2;
import com.dj.ssm.utils.SysConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {

    private String a;
    private String b;
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping("login")
    private ResultModel<Object> login(User user, HttpSession session){
        try {
           User selUser = userService.findUserByUserNameAndUserPwd(user);
           if (selUser == null){
               return new ResultModel<Object>().error("用户名，密码错误");
           }

           //登陆成功，跟据登陆人的id查询token表，有的话修改时间和token,没有的话添加记录
            Token t = tokenService.findTokenByUserId(selUser.getId());
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE, 3);
            String userToken = UUID.randomUUID().toString().replace("-", "");
            if(t == null){
               Token addToken = new Token()
                       .setUserId(selUser.getId())
                       .setValidTime(cal.getTime())
                       .setToken(userToken);
               tokenService.addToken(addToken);
           }else{
               t.setToken(userToken);
               t.setValidTime(cal.getTime());
               tokenService.updateToken(t);
           }
            Map<String, Object> map = new HashMap<String, Object>();
           map.put("token", userToken);
            return new ResultModel<Object>().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常请稍后重试");
        }
    }

    @RequestMapping("show")
    public ResultModel<Object> show(Integer pageNo) {
        Map<String, Object> map = new HashMap<>();
        try {
            PageHelper.startPage(pageNo, SysConstant.PAGE_SIZE);
            List<User> list = userService.findAll(SysConstant.IS_DEL_1);
            PageInfo<User> pageInfo = new PageInfo<User>(list);
            map.put("pages", pageInfo.getPages());
            map.put("list", pageInfo.getList());
            return new ResultModel<Object>().success(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常");
        }
    }

    @RequestMapping("addUser")
    public ResultModel<Object> addUser(User user, String token, MultipartFile file) {
        try {
            Token selToken = tokenService.findTokenByUserToken(token);
            user.setIsDel(SysConstant.IS_DEL_1);
            String fileName = QinniuUtil2.updateFile(file);
            user.setFileName(fileName);
            userService.addUser(user);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常");
        }
    }

    @RequestMapping("updateUser")
    public ResultModel<Object> updateUser(User user, String token) {
        try {
            Token selToken = tokenService.findTokenByUserToken(token);
            User disUser = userService.findUserByUserName(user.getUserName());
            if(null != disUser && disUser.getId() != user.getId()){
                return new ResultModel<Object>().error("用户名已重复");
            }
            userService.updateUser(user);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常");
        }
    }


    @RequestMapping("updateUserIsDel")
    public ResultModel<Object> updateUserIsDel(Integer id) {
        try {
            userService.updateUser(new User().setId(id).setIsDel(SysConstant.IS_DEL_0));
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常");
        }
    }

    @RequestMapping("findUserByUserName")
    public Boolean findUserByUserName(String userName) {
        try {
            User user = userService.findUserByUserName(userName);
            return user == null ? true : false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }


    }

}
