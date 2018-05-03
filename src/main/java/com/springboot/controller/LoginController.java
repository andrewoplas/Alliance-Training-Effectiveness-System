package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.service.LoginService;
import com.springboot.service.RegisterService;

@Controller
@RequestMapping("/login")
public class LoginController{
		
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public String check(HttpServletRequest request, ModelMap map) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean error = loginService.searchUser(email, password);
		
		// User exists
		if(error == false) {
			
		}
		
		// Display error
		map.addAttribute("response", error);
		return "login";
	}

	

//	@RequestMapping(method = RequestMethod.POST)
//	public String executeLogin(HttpServletRequest request, ModelMap map) {
//		// Accept inputs
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		// Validate
//		boolean isValid = loginService.validate(username, password);
//		
//		// Map response
//		map.addAttribute("isValid", isValid);
//		
//		return "login";
//	}
}