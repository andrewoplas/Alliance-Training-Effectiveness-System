package com.springboot.controller;

import java.util.List;
import java.util.Map;

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

import com.springboot.body.Answer;
import com.springboot.body.AnswerSA;
import com.springboot.body.AssignmentSA;
import com.springboot.entities.Form;
import com.springboot.entities.FormAssignment;
import com.springboot.entities.SaAssignment;
import com.springboot.entities.SaCategory;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.User;
import com.springboot.service.FormsService;
import com.springboot.service.TrainingPlanService;
import com.springboot.service.UserFormsService;
import com.springboot.service.UserTrainingService;


@Controller
@RequestMapping("/ates/general")
public class UserFormsController {
	
	@Autowired
	private UserTrainingService tpService;
	
	@Autowired
	private TrainingPlanService adminTPService;
	
	@Autowired
	private FormsService formsService;
	
	@Autowired
	private UserFormsService ufService;
	
	@Autowired
	private SessionController session;
	
	
	@RequestMapping(value = "/training/skills-assessment/assignment/{userEventID}")
	public String showAssignment(@PathVariable int userEventID, ModelMap map) {
		List<SaAssignment> assignments = ufService.retrieveAssignmentAssigned(userEventID);
		
		map.addAttribute("assignments", assignments);
		
		return "/general/training/skills-assessment/assignment";
	}
	
	@RequestMapping(value = "/training/skills-assessment/{id}/{trainingPlanId}")
	public String skillsAssessment(ModelMap map, HttpServletRequest request, @PathVariable int id, @PathVariable String trainingPlanId) {
		User user = session.getUser(request);
		if(!tpService.checkTrainingInvolvementAndSupervisor(user, id)) return "redirect:/error/404";
		
		TrainingPlan training = adminTPService.retrieveTraining(trainingPlanId);
		
		if(training != null) {
			Map<Integer, List<Integer>> assignments = ufService.retrieveAssignments(training.getId());
			List<User> participants = adminTPService.retrieveTrainingPeople(training, "Participant", false);
			List<User> supervisors = adminTPService.retrieveTrainingPeople(training, "Supervisor", false);
			
			map.addAttribute("assignments", assignments);
			map.addAttribute("training", training);
			map.addAttribute("participants", participants);
			map.addAttribute("supervisors", supervisors);
			map.addAttribute("userEventId", id);
		} else {
			return "redirect:/error/404";
		}
		
		return "/general/training/skills-assessment";
	}
	
	@RequestMapping(value = "/training/skills-assessment/{trainingPlanId}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertTraining(@RequestBody AssignmentSA[] assessment, @PathVariable String trainingPlanId, HttpServletRequest request) {
			
		ufService.insertAssignment(assessment, Integer.parseInt(trainingPlanId));
		
		return ResponseEntity.ok(true);
	}
	
	@RequestMapping(value = "/training/skills-assessment/answer/{assignmentID}")
	public String answer(@PathVariable int assignmentID, ModelMap map) {
		SaAssignment assignment = ufService.retrieveAssignmentById(assignmentID);
		
		if(assignment != null && assignment.getStatus().equals("unanswered")) {
			List<SaCategory> parents = formsService.getParentCategories();

			map.addAttribute("categories", parents);
			map.addAttribute("assignment", assignment);
		} else {
			return "redirect:/error/404";
		}
		
		return "/general/training/skills-assessment/answer";
	}
	
	@RequestMapping(value = "/training/skills-assessment/answer", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertAnswer(@RequestBody AnswerSA answers) {
		boolean result = ufService.insertAnswers(answers);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/training/skills-assessment/view/{assignmentID}")
	public String viewAnswer(@PathVariable int assignmentID, ModelMap map) {
		SaAssignment assignment = ufService.retrieveAssignmentById(assignmentID);
		
		if(assignment != null && assignment.getStatus().equals("answered")) {
			List<SaCategory> parents = formsService.getParentCategories();

			map.addAttribute("categories", parents);
			map.addAttribute("assignment", assignment);
		} else {
			return "redirect:/error/404";
		}
		
		return "/general/training/skills-assessment/view";
	}
	
	@RequestMapping(value = "/training/form/answer/{assignmentID}")
	public String answerForm(@PathVariable int assignmentID, ModelMap map) {
		FormAssignment assignment = ufService.retrieveFormAssignmentById(assignmentID);
		
		if(assignment != null && assignment.getStatus().equals("unanswered")) {
			Form form = assignment.getForm();
			
			map.addAttribute("form", form);
			map.addAttribute("questions", form.getFormQuestions());
			map.addAttribute("assignment", assignment);
		} else {
			return "redirect:/error/404";
		}
		
		return "/general/training/form/answer";
	}
	
	@RequestMapping(value = "/training/form/answer/{assignmentID}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertFormAnswer(@PathVariable int assignmentID, @RequestBody Answer[] answers) {
		ufService.insertAnswers(answers, assignmentID);
		
		return ResponseEntity.ok(true);
	}
}
