package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
		
	@Autowired
	RegisterService registerService;
	@RequestMapping(value = { "", "/" }, method=RequestMethod.GET)
	public String index() {
		return "register";
	}
	
	
	@RequestMapping(value = "/saveRegistration",method=RequestMethod.POST)
	public String SaveRegistration(HttpServletRequest request, ModelMap map) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String position = request.getParameter("position");
		String password = request.getParameter("password");
		String confpassword = request.getParameter("confirm_password");
		
		if(password.equals(confpassword))
		{
			registerService.insertUser(name,email,position,password);
		}
		else
		{
			//INSERT WRONG INPUT MESSAGE
		}
		
		return "register";
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