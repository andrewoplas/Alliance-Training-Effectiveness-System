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

import com.springboot.body.Question;
import com.springboot.body.SkillsAssessment;
import com.springboot.entities.Form;
import com.springboot.entities.SaAssignment;
import com.springboot.entities.SaCategory;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;
import com.springboot.service.FormsService;
import com.springboot.service.TrainingPlanService;
import com.springboot.service.UserTrainingService;


@Controller
@RequestMapping("/ates/forms")
public class FormsController {
	
	@Autowired
	FormsService formsService;
	
	@Autowired
	TrainingPlanService tpService;
	
	@Autowired
	UserTrainingService utpService;
	
	@RequestMapping(value = "/assignment")
	public String formsAssignment(ModelMap map) {
		List<TrainingPlan> trainings = tpService.retrieveTrainings();
		
		map.addAttribute("trainings", trainings);
		
		return "/forms/assignment";
	}

	@RequestMapping(value = "/skills-assessment")
	public String skillsAssessment(ModelMap map, HttpServletRequest request) {
		List<SaCategory> parents = formsService.getParentCategories();

		map.addAttribute("categories", parents);
		
		return "/forms/skillsAssessment";
	}
	
	@RequestMapping(value = "/skills-assessment", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertSkillsAssessment(@RequestBody SkillsAssessment[] sa, HttpServletRequest request) {		
		formsService.insertSkillsAssessment(sa);
		
		return ResponseEntity.ok(true);
	}
	
	@RequestMapping(value = "/skills-assessment/view/{trainingPlanID}")
	public String skillsAssessment(ModelMap map, @PathVariable String trainingPlanID) {
		TrainingPlan training = tpService.retrieveTraining(trainingPlanID);
		
		if(training != null) {
			List<UserEvent> participants = tpService.retrieveTrainingUserEvent(training, "Participant", false);
			
			map.addAttribute("training", training);
			map.addAttribute("userEvents", participants);
		} else {
			return "redirect:/error/404";
		}
		
		return "/forms/skillsAssessment/view";
	}
	
	@RequestMapping(value = "/skills-assessment/view/answer/{assignmentID}")
	public String viewAnswersAssessment(@PathVariable int assignmentID, ModelMap map) {
		SaAssignment assignment = utpService.retrieveAssignmentById(assignmentID);
		
		if(assignment != null && assignment.getStatus().equals("answered")) {
			List<SaCategory> parents = formsService.getParentCategories();

			map.addAttribute("categories", parents);
			map.addAttribute("assignment", assignment);
		} else {
			return "redirect:/error/404";
		}
		
		return "/forms/skills-assessment/view/single";
	}
	
	@RequestMapping(value = "/course-feedback")
	public String courseFeedback(ModelMap map) {
		Form form = formsService.retrieveForm(1);
		
		map.addAttribute("form", form);
		map.addAttribute("questions", form.getFormQuestions());
		
		return "/forms/formConfig";
	}
	
	@RequestMapping(value = "/facilitator-feedback")
	public String facilitatorFeedback(ModelMap map) {
		Form form = formsService.retrieveForm(2);
		
		map.addAttribute("form", form);
		map.addAttribute("questions", form.getFormQuestions());
		
		return "/forms/formConfig";
	}
	
	@RequestMapping(value = "/training-effectivess-assessment")
	public String trainingEffectivenessAssessment(ModelMap map) {
		Form form = formsService.retrieveForm(3);
		
		map.addAttribute("form", form);
		map.addAttribute("questions", form.getFormQuestions());
		
		return "/forms/formConfig";
	}
	
	@RequestMapping(value = "/questions/{formID}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertQuestions(@PathVariable int formID, @RequestBody Question[] questions) {		
		
		formsService.insertQuestions(questions, formID);
		
		return ResponseEntity.ok(true);
	}
	
}
