package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.entities.TrainingPlan;
import com.springboot.service.TrainingPlanService;
import com.springboot.service.UsersService;

@Controller
@RequestMapping("/ates")
public class DashboardController {
	
	@Autowired
	private TrainingPlanService tpService;
	
	@Autowired
	private UsersService usersService;
	
		
	@RequestMapping(value = "/dashboard")
	public String index(ModelMap map) {
		List<TrainingPlan> trainings = tpService.retrieveTrainings();
		int userCount = usersService.retrieveApprovedUsers().size();
		int pendingUserCount = usersService.retrievePendingUsers().size();
		int trainingCount = trainings.size();
		int trainingRequestCount = 0;
		
		map.addAttribute("userCount", userCount);
		map.addAttribute("pendingUserCount", pendingUserCount);
		map.addAttribute("trainingCount", trainingCount);
		map.addAttribute("trainingRequestCount", trainingRequestCount);
		
		return "dashboard";
	}
	
	
}