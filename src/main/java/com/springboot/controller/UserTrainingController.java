package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.Schedule;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.User;
import com.springboot.entities.UserEvent;
import com.springboot.entities.custom.CustomSchedule;
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
			List<User> participants = adminTPService.retrieveTrainingPeople(training, "Participant");
			List<User> internal = adminTPService.retrieveTrainingPeople(training, "Internal");
			List<User> external = adminTPService.retrieveTrainingPeople(training, "External");
			List<User> supervisors = adminTPService.retrieveTrainingPeople(training, "Supervisor");
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
}
