package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.Position;
import com.springboot.service.PositionService;
import com.springboot.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
		
	@Autowired
	RegisterService registerService;
	
	@Autowired
	PositionService positionService;
	
	@RequestMapping(value = { "", "/" }, method=RequestMethod.GET)
	public String index(ModelMap map) {
		List<Position> positions = positionService.retrievePositions();
		
		map.addAttribute("positions", positions);
		return "register";
	}
	
	@RequestMapping(value = "/saveRegistration", method = RequestMethod.POST)
	public ResponseEntity<?> register(HttpServletRequest request, ModelMap map) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String position = request.getParameter("position");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm_password");
		
		String response = registerService.insertUser(name, email, position, password, confirm);
		return ResponseEntity.ok(response == null);
	}
	
	
	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
	public ResponseEntity<?> contains(HttpServletRequest request, ModelMap map) {
		String email = request.getParameter("email");
				
		boolean response = registerService.containsUserByEmail(email);
		
		return ResponseEntity.ok(response);
	}
}