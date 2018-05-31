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
	private UserTrainingService tpService;
	
	@Autowired
	private TrainingPlanService adminTPService;
	
	@Autowired
	private SessionController session;
	
	
	@RequestMapping(value = "/dashboard")
	public String dashboard(ModelMap map, HttpServletRequest request) {
		// Display dashboard of user with data
		User user = session.getUser(request);
		
		return "/general/dashboard";
	}
	
	@RequestMapping(value = "/training")
	public String displayTrainings(ModelMap map, HttpServletRequest request) {
		// Display table of trainings that user is involved
		User user = session.getUser(request);
		
		List<UserEvent> userEvents = tpService.retrieveUserEvent(user.getId());
		map.addAttribute("userEvents", userEvents);

		return "/general/training";
	}
	
	@RequestMapping(value = "/training/{id}/{trainingPlanId}")
	public String summary(ModelMap map, HttpServletRequest request, @PathVariable String id, @PathVariable String trainingPlanId) {
		// Display the summary of training that user is involved
		User user = session.getUser(request);
		
		if(!tpService.checkTrainingInvolvement(user, id)) 
			return "redirect:/error/404";
		
		TrainingPlan training = adminTPService.retrieveTraining(trainingPlanId);
		
		if(training != null) {
			boolean showApprovedOnly = false;			
			List<User> participants = adminTPService.retrieveTrainingPeople(training, "Participant", showApprovedOnly);
			List<User> internal = adminTPService.retrieveTrainingPeople(training, "Internal", showApprovedOnly);
			List<User> external = adminTPService.retrieveTrainingPeople(training, "External", showApprovedOnly);
			List<User> supervisors = adminTPService.retrieveTrainingPeople(training, "Supervisor", showApprovedOnly);
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
	public String editTraining(ModelMap map, HttpServletRequest request, @PathVariable String id, @PathVariable String trainingPlanId) {
		// Display the form to edit the form (for facilitators only)
		User user = session.getUser(request);
		if(!tpService.checkTrainingInvolvementAndFacilitator(user, id)) 
			return "redirect:/error/404";
		
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
		// Request to edit the training plan (for facilitators only)
		User user = session.getUser(request);
		String id = request.getParameter("id");
		String userEventId = request.getParameter("userEventId");
		String description = request.getParameter("description");
		String outline = request.getParameter("outline");
		
		if(!tpService.checkTrainingInvolvementAndFacilitator(user, userEventId)) 
			return ResponseEntity.ok("not allowed");
		
		boolean result = tpService.editTraining(id, description, outline);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/invitation")
	public String displayInvitations(ModelMap map, HttpServletRequest request) {
		// Display the training invitations of user
		User user = session.getUser(request);
		
		List<UserEvent> userEvents = tpService.retrievePendingUserEvent(user.getId());
		map.addAttribute("userEvents", userEvents);

		return "/general/invitation";
	}
	
	@RequestMapping(value = "/invitation/accept", method = RequestMethod.POST)
	public ResponseEntity<?> acceptInvitation(HttpServletRequest request) {
		// Request to accept invitation
		boolean result = tpService.acceptInvitation(request.getParameter("id"));
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/invitation/decline", method = RequestMethod.POST)
	public ResponseEntity<?> declineInvitation(HttpServletRequest request) {
		// Requeset to decline invitation
		boolean result = tpService.declineInvitation(request.getParameter("id"));
		
		return ResponseEntity.ok(result);
	} 
	
	@RequestMapping(value = "/training/getTrainings", method = RequestMethod.GET)
	public ResponseEntity<?> getTrainings(HttpServletRequest request) {
		// Request to get schedule of trainings
		User user = session.getUser(request);
		
		List<CustomSchedule> schedules = tpService.retrieveUserTrainingCustomSchedules(user.getId());
		
		return ResponseEntity.ok(schedules);
	}
	
	@RequestMapping(value = "/notification", method = RequestMethod.GET)
	public ResponseEntity<?> notification(HttpServletRequest request) {
		// Request to get the pending invitation
		User user = session.getUser(request);
		
		int result = tpService.retrievePendingUserEvent(user.getId()).size();
		
		return ResponseEntity.ok(result > 0);
	}
}
