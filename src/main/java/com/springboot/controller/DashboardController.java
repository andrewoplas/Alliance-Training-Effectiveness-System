package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.entities.Form;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;
import com.springboot.service.FormsService;
import com.springboot.service.TrainingPlanService;
import com.springboot.service.UsersService;

@Controller
@RequestMapping("/ates")
public class DashboardController {
	
	@Autowired
	private TrainingPlanService tpService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FormsService formsService;
	
		
	@RequestMapping(value = "/dashboard")
	public String index(ModelMap map) {
		// Display information on dashboard
		int userCount = usersService.retrieveApprovedUsers().size();
		int pendingUserCount = usersService.retrievePendingUsers().size();
		int trainingCount = tpService.retrieveTrainings().size();
		int trainingRequestCount = 0;
		
		List<UserEvent> userEvents = tpService.retrieveUserEvents();
		List<List<UserEvent>> userEventsQuarter = tpService.retrieveTrainingByQuarter();
		List<List<UserEvent>> userEventsMonth = tpService.retrieveTrainingByMonth();
		
		// Statistics
		map.addAttribute("formID", 3); // Annual
		map.addAttribute("questions", formsService.retrieveForm(3).getFormQuestions());
		map.addAttribute("userEvents", userEvents); // Annual
		map.addAttribute("userEventsQuarter", userEventsQuarter); // Quarter
		map.addAttribute("userEventsMonth", userEventsMonth); // Month
		
		// Top Counts
		map.addAttribute("userCount", userCount);
		map.addAttribute("pendingUserCount", pendingUserCount);
		map.addAttribute("trainingCount", trainingCount);
		map.addAttribute("trainingRequestCount", trainingRequestCount);
		
		return "dashboard";
	}
	
	
}