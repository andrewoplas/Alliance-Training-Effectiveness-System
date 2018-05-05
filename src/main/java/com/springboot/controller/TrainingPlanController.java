package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.Trainingplan;
import com.springboot.service.TrainingPlanService;

@Controller
@RequestMapping("/trainingplan")
public class TrainingPlanController {
	
	@Autowired
	private TrainingPlanService trainingPlanService;

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String loadAddTrainingPlan() {
		return "trainingplan/add"; 
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addTrainingPlan(HttpServletRequest request) {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		int schedId = Integer.parseInt(request.getParameter("schedId"));
		trainingPlanService.addTrainingPlan(title, description,schedId);
		return "trainingplan/list";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public String loadGetTrainingPlan(){
		return "trainingplan/get";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getTrainingPlan(ModelMap map){
		List<Trainingplan> tPlanList = trainingPlanService.getTrainingPlan();
		map.addAttribute("resultTPlans", tPlanList);
		return "trainingplan/list";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.POST)
	public String getTrainingPlanByName(HttpServletRequest request, ModelMap map){
		String tTitle = request.getParameter("title");
		List<Trainingplan> tPlanList = trainingPlanService.getTrainingPlanByTitle(tTitle);
		map.addAttribute("resultTPlans", tPlanList);
		return "trainingplan/get";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String loadUpdateTrainingPlan(HttpServletRequest request, ModelMap map){
		String tId = request.getParameter("id");
		Trainingplan tPlan = trainingPlanService.getSingleTrainingPlanById(tId);
		map.addAttribute("trainingPlan",tPlan);
		return "trainingplan/update";
	}
	
	@RequestMapping(value="/updating",method=RequestMethod.POST)
	public String UpdateTrainingPlan(HttpServletRequest request, ModelMap map){
		String tId = request.getParameter("id");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		int schedId = Integer.parseInt(request.getParameter("schedId"));
		
		Trainingplan tPlan = new Trainingplan();
		tPlan.setTitle(title);
		tPlan.setDescription(description);
		tPlan.setSid(schedId);
		tPlan = trainingPlanService.updateTrainingPlan(tPlan,tId);
		map.addAttribute("trainingPlan", tPlan);

		return getTrainingPlan(map);
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String removeTrainingPlan(HttpServletRequest request, ModelMap map){
		String tId = request.getParameter("id");
		trainingPlanService.removeTrainingPlanById(tId);
		return getTrainingPlan(map);
	}
//	@RequestMapping(value="/remove",method=RequestMethod.POST)
//	public String removeTrainingPlan(HttpServletRequest request, ModelMap map) {
//		String[] idsToDelete = request.getParameterValues("trainingPlansSelected");
//		trainingPlanService.removeTrainingPlansById(idsToDelete);	
//		return getTrainingPlan(map);
//	}
}
