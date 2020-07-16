package com.dj.ssm.controller.config;

import com.dj.ssm.pojo.Token;
import com.dj.ssm.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

public class MyInterceptors implements HandlerInterceptor{

	@Autowired
	private TokenService tokenService;
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView andView)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String token = request.getParameter("token");
		if(null == token) {
			response.sendRedirect(request.getContextPath() + "/user/toLogin");
			return false;
		}
		Token selToken = tokenService.findTokenByUserToken(token);
		if(null == selToken){
			response.sendRedirect(request.getContextPath() + "/user/toLogin");
			return false;
		}
		if(new Date().getTime() > selToken.getValidTime().getTime()){
			response.sendRedirect(request.getContextPath() + "/user/toLogin");
			return false;
		}
		//若都满足条件，将时间和token更新
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MINUTE, 3);
		selToken.setValidTime(c.getTime());
		tokenService.updateToken(selToken);
		return true;
	}

}
