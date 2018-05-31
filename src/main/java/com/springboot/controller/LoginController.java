package com.springboot.controller;

import java.io.IOException;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.User;
import com.springboot.service.LoginService;

@Controller
@RequestMapping("login")
public class LoginController{
	
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value = { "", "/" })
	public String index() {
		// Show login page
		return "login";
	}
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws MessagingException {
		// Request to login
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = loginService.searchUser(email, password);
		
		// User exists and approved
		if(user != null && !(user.getStatus().equals("pending"))) {
			// Set the session
			request.getSession().setAttribute("isLoggedIn", user);			
			
			try {
				// Redirect to user's dashboard
				if(user.getIsAdmin() == 1) {
					response.sendRedirect(request.getContextPath() + "/ates/dashboard");
				} else {
					response.sendRedirect(request.getContextPath() + "/ates/general/dashboard");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Display error
		map.addAttribute("response", (user == null));
		
		return "login";
	}
	
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidate session
		request.getSession().invalidate();
		
		try {
			// Redirect to login page
			response.sendRedirect(request.getContextPath() + "/login");
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}