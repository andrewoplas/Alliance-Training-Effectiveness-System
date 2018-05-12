package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.User;
import com.springboot.service.UsersService;


@Controller
@RequestMapping("/ates/training")
public class TrainingPlanController {
	
	@Autowired
	UsersService usersService;
	
	@RequestMapping(value = { "", "/" })
	public String index() {
		return "pages/trainingPlan";
	}
	
	@RequestMapping(value = "/create")
	public String create(ModelMap map) {
		List<User> users = usersService.retrieveApprovedUsers();
		
		map.addAttribute("users", users);
		return "pages/trainingPlan";
	}
	
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	public ResponseEntity<?> create(HttpServletRequest request) {
		System.out.println(request.getParameter("trainingPlan"));
		
		
		return ResponseEntity.ok(true);
	}

	
}
