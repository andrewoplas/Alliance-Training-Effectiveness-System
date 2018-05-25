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

import com.springboot.body.AnswerSA;
import com.springboot.body.AssignmentSA;
import com.springboot.entities.SaAssignment;
import com.springboot.entities.SaCategory;
import com.springboot.entities.Schedule;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.User;
import com.springboot.entities.UserEvent;
import com.springboot.entities.custom.CustomSchedule;
import com.springboot.service.FormsService;
import com.springboot.service.TrainingPlanService;
import com.springboot.service.UserTrainingService;


@Controller
@RequestMapping("/ates/general")
public class UserTrainingController {
	
	@Autowired
	UserTrainingService tpService;
	
	@Autowired
	TrainingPlanService adminTPService;
	
	@Autowired
	FormsService formsService;
	
	@Autowired
	SessionController session;
	
	@RequestMapping(value = "/dashboard")
	public String dashboard(ModelMap map, HttpServletRequest request) {
		User user = session.getUser(request);
		
		return "/general/dashboard";
	}
	
	@RequestMapping(value = "/training")
	public String trainings(ModelMap map, HttpServletRequest request) {
		User user = session.getUser(request);
		
		List<UserEvent> userEvents = tpService.retrieveUserEvent(user.getId());
		map.addAttribute("userEvents", userEvents);

		return "/general/training";
	}
	
	@RequestMapping(value = "/training/{id}/{trainingPlanId}")
	public String viewTraining(ModelMap map, HttpServletRequest request, @PathVariable int id, @PathVariable String trainingPlanId) {
		User user = session.getUser(request);
		if(!tpService.checkTrainingInvolvement(user, id)) return "redirect:/error/404";
		
		TrainingPlan training = adminTPService.retrieveTraining(trainingPlanId);
		
		if(training != null) {
			List<User> participants = adminTPService.retrieveTrainingPeople(training, "Participant", true);
			List<User> internal = adminTPService.retrieveTrainingPeople(training, "Internal", true);
			List<User> external = adminTPService.retrieveTrainingPeople(training, "External", true);
			List<User> supervisors = adminTPService.retrieveTrainingPeople(training, "Supervisor", true);
			List<Schedule> schedules = adminTPService.sortSchedule(training.getSchedules());
						
			map.addAttribute("participants", participants);
			map.addAttribute("supervisors", supervisors);
			map.addAttribute("internal", internal);
			map.addAttribute("external", external);
			map.addAttribute("schedules", schedules);	
			map.addAttribute("training", training);
		} else {
			return "redirect:/error/404";
		}
		
		return "/general/training/summary";
	}
	
	@RequestMapping(value = "/training/edit/{id}/{trainingPlanId}")
	public String editTraining(ModelMap map, HttpServletRequest request, @PathVariable int id, @PathVariable String trainingPlanId) {
		User user = session.getUser(request);
		if(!tpService.checkTrainingInvolvementAndFacilitator(user, id)) return "redirect:/error/404";
		
		TrainingPlan training = adminTPService.retrieveTraining(trainingPlanId);
		
		if(training != null) {
			map.addAttribute("training", training);
			map.addAttribute("userEventId", id);
		} else {
			return "redirect:/error/404";
		}
		
		return "/general/training/edit";
	}
	
	@RequestMapping(value = "/training/edit", method = RequestMethod.POST)
	public ResponseEntity<?> editTrainingPlan(ModelMap map, HttpServletRequest request) {
		User user = session.getUser(request);
		String id = request.getParameter("id");
		String userEventId = request.getParameter("userEventId");
		String description = request.getParameter("description");
		String outline = request.getParameter("outline");
		
		if(!tpService.checkTrainingInvolvementAndFacilitator(user, Integer.parseInt(userEventId))) return ResponseEntity.ok("not allowed");
		
		tpService.editTraining(id, description, outline);
		
		
		return ResponseEntity.ok(true);
	}
	
	@RequestMapping(value = "/invitation")
	public String invitations(ModelMap map, HttpServletRequest request) {
		User user = session.getUser(request);
		
		List<UserEvent> userEvents = tpService.retrievePendingUserEvent(user.getId());
		map.addAttribute("userEvents", userEvents);

		return "/general/invitation";
	}
	
	@RequestMapping(value = "/invitation/accept", method = RequestMethod.POST)
	public ResponseEntity acceptInvitation(HttpServletRequest request) {
		String id = request.getParameter("id");		
		
		tpService.acceptInvitation(id);
		
		return ResponseEntity.ok(id);
	}
	
	@RequestMapping(value = "/invitation/decline", method = RequestMethod.POST)
	public ResponseEntity declineInvitation(HttpServletRequest request) {
		String id = request.getParameter("id");		
		
		tpService.declineInvitation(id);
		
		return ResponseEntity.ok(id);
	}
	
	@RequestMapping(value = "/training/getTrainings", method = RequestMethod.GET)
	public ResponseEntity<?> getTrainings(HttpServletRequest request) {
		User user = session.getUser(request);
		
		List<CustomSchedule> schedules = tpService.retrieveUserTrainingCustomSchedules(user.getId());
		
		return ResponseEntity.ok(schedules);
	}
	
	@RequestMapping(value = "/notification", method = RequestMethod.GET)
	public ResponseEntity<?> notification(HttpServletRequest request) {
		User user = session.getUser(request);
		
		int result = tpService.retrievePendingUserEvent(user.getId()).size();
		
		return ResponseEntity.ok(result > 0);
	}
	
	
	@RequestMapping(value = "/training/skills-assessment/assignment/{userEventID}")
	public String showAssignment(@PathVariable int userEventID, ModelMap map) {
		List<SaAssignment> assignments = tpService.retrieveAssignmentAssigned(userEventID);
		
		map.addAttribute("assignments", assignments);
		
		return "/general/training/skills-assessment/assignment";
	}
	
	@RequestMapping(value = "/training/skills-assessment/{id}/{trainingPlanId}")
	public String skillsAssessment(ModelMap map, HttpServletRequest request, @PathVariable int id, @PathVariable String trainingPlanId) {
		User user = session.getUser(request);
		if(!tpService.checkTrainingInvolvementAndSupervisor(user, id)) return "redirect:/error/404";
		
		TrainingPlan training = adminTPService.retrieveTraining(trainingPlanId);
		
		if(training != null) {
			Map<Integer, List<Integer>> assignments = tpService.retrieveAssignments(training.getId());
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
			
		tpService.insertAssignment(assessment, Integer.parseInt(trainingPlanId));
		
		return ResponseEntity.ok(true);
	}
	
	@RequestMapping(value = "/training/skills-assessment/answer/{assignmentID}")
	public String answer(@PathVariable int assignmentID, ModelMap map) {
		SaAssignment assignment = tpService.retrieveAssignmentById(assignmentID);
		
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
		boolean result = tpService.insertAnswers(answers);
		
		return ResponseEntity.ok(true);
	}
	
	@RequestMapping(value = "/training/skills-assessment/view/{assignmentID}")
	public String viewAnswersAssessment(@PathVariable int assignmentID, ModelMap map) {
		SaAssignment assignment = tpService.retrieveAssignmentById(assignmentID);
		
		if(assignment != null && assignment.getStatus().equals("answered")) {
			List<SaCategory> parents = formsService.getParentCategories();

			map.addAttribute("categories", parents);
			map.addAttribute("assignment", assignment);
		} else {
			return "redirect:/error/404";
		}
		
		return "/general/training/skills-assessment/view";
	}
}
