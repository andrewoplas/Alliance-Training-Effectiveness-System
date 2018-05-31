package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.springboot.entities.User;

@Controller
public class SessionController {
	
	public User getUser(HttpServletRequest request) {
		// Get the user session
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("isLoggedIn");
		
		return user;
	}	
}