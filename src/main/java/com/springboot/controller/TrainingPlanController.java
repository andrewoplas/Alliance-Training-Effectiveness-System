package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.body.Training;
import com.springboot.entities.Position;
import com.springboot.entities.TrainingPlan;
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
	
	@RequestMapping(value = "/list")
	public String list(ModelMap map) {
		List<TrainingPlan> trainings = tpService.retrieveTrainings();
		
		map.addAttribute("trainings", trainings);
		
		return "/training/list";
	}
	
	@RequestMapping(value = "/create")
	public String create(ModelMap map) {
		List<User> users = usersService.retrieveApprovedUsers();
		
		map.addAttribute("users", users);
		return "/training/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertTraining(@RequestBody Training training, HttpServletRequest request) {		
		boolean result = tpService.insertTraining(training);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(ModelMap map, @PathVariable String id) {
		List<User> users = usersService.retrieveApprovedUsers();		
		TrainingPlan training = tpService.retrieveTraining(id);
		
		map.addAttribute("users", users);
		if(training != null) {
			map.addAttribute("training", training);
		}		
		
		return "/training/edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> editTraining(@RequestBody Training training, HttpServletRequest request) {		
		boolean result = tpService.editTraining(training);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTraining(HttpServletRequest request) {		
		boolean result = tpService.deleteTraining(request.getParameter("id"));
		
		return ResponseEntity.ok(result);
	}
}
