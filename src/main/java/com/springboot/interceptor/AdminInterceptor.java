package com.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springboot.entities.User;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("isLoggedIn") != null) {
			User user = (User) session.getAttribute("isLoggedIn");
			if(user.getIsAdmin() == 0) {
				response.sendRedirect(request.getContextPath() + "/ates/dashboard");
				return false;
			}
		}
		
		return true;
	}
}
