package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.body.Training;
import com.springboot.entities.User;
import com.springboot.service.TrainingPlanService;
import com.springboot.service.UsersService;


@Controller
@RequestMapping("/ates/training")
public class TrainingPlanController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	TrainingPlanService tpService;
	
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
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertTraining(@RequestBody Training training, HttpServletRequest request) {		
		boolean result = tpService.insertTraining(training);
		
		return ResponseEntity.ok(result);
	}

	
}
