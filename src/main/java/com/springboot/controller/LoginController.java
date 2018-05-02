package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {
		
	@RequestMapping(value = { "", "/" }, method=RequestMethod.GET)
	public String index() {
		return "auth/login";
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