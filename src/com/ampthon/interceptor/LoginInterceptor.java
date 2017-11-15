package com.ampthon.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ampthon.pojo.TabUser;
import com.ampthon.service.TabUserService;
import com.ampthon.util.Const;




/**
 * 登陆拦截器
 * 
 * @author gl
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private TabUserService tabUserService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String strurl="";
		HttpSession session= null;
		TabUser userinfo=null;
		System.out.println(request.getMethod()+"     "+request.getRequestURI()+"---end");
		
		
		Object sessionUserObject = request.getSession().getAttribute(Const.SESSION_KEY_USER);  
	
		return true;
	}

}
