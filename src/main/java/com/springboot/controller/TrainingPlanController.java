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
import com.springboot.entities.Schedule;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.User;
import com.springboot.entities.UserEvent;
import com.springboot.entities.custom.CustomSchedule;
import com.springboot.service.TrainingPlanService;
import com.springboot.service.UsersService;


@Controller
@RequestMapping("/ates/training")
public class TrainingPlanController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private TrainingPlanService tpService;
	
	
	@RequestMapping(value = "/list")
	public String displayTraining(ModelMap map) {
		// Display table of trainings created
		List<TrainingPlan> trainings = tpService.retrieveTrainings();
		
		map.addAttribute("trainings", trainings);
		
		return "/training/list";
	}
	
	@RequestMapping(value = "/list/calendar")
	public String displayCalendar(ModelMap map) {
		// Display the calendar on a full view
		return "/training/list/calendar";
	}
	
	@RequestMapping(value = "/invitation-status/{trainingPlanID}")
	public String displayInvitationStatus(ModelMap map, @PathVariable String trainingPlanID) {
		// Display table of users' invitation status of a certain training
		TrainingPlan training = tpService.retrieveTraining(trainingPlanID);
		
		if(training != null) {
			int[] status = tpService.getTrainingStatus(training);
			
			map.addAttribute("training", training);
			map.addAttribute("userEvents", training.getUserEvents());
			map.addAttribute("approvedCount", status[0]);
			map.addAttribute("declinedCount", status[1]);
			map.addAttribute("pendingCount", status[2]);
		} else {
			return "redirect:/error/404";
		}
		
		return "/training/invitation-status";
	}
	
	@RequestMapping(value = "/{id}")
	public String summary(ModelMap map, @PathVariable String id) {
		// Display training's summary
		TrainingPlan training = tpService.retrieveTraining(id);
		
		if(training != null) {
			boolean showApprovedOnly = false;
			List<User> participants = tpService.retrieveTrainingPeople(training, "Participant", showApprovedOnly);
			List<User> internal = tpService.retrieveTrainingPeople(training, "Internal", showApprovedOnly);
			List<User> external = tpService.retrieveTrainingPeople(training, "External", showApprovedOnly);
			List<User> supervisors = tpService.retrieveTrainingPeople(training, "Supervisor", showApprovedOnly);
			List<Schedule> schedules = tpService.sortSchedule(training.getSchedules());
						
			map.addAttribute("participants", participants);
			map.addAttribute("supervisors", supervisors);
			map.addAttribute("internal", internal);
			map.addAttribute("external", external);
			map.addAttribute("schedules", schedules);	
			map.addAttribute("training", training);
		} else {
			return "redirect:/error/404";
		}
		
		return "/training/summary";
	}
	
	@RequestMapping(value = "/create")
	public String createTraining(ModelMap map) {
		// Display the form to create a training
		List<User> users = usersService.retrieveApprovedUsers();
		
		map.addAttribute("users", users);
		map.addAttribute("map", true);
		return "/training/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertTraining(@RequestBody Training training, HttpServletRequest request) {
		// Request to insert training
		boolean result = tpService.insertTraining(training);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editTraining(ModelMap map, @PathVariable String id) {
		// Display the form to edit training
		List<User> users = usersService.retrieveApprovedUsers();		
		TrainingPlan training = tpService.retrieveTraining(id);
		
		map.addAttribute("users", users);
		map.addAttribute("map", true);
		if(training != null) {
			map.addAttribute("training", training);
		} else {
			return "redirect:/error/404";
		}
		
		return "/training/edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> editTraining(@RequestBody Training training, HttpServletRequest request) {
		// Request to edit training
		boolean result = tpService.editTraining(training);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTraining(HttpServletRequest request) {	
		// Request to delete training
		boolean result = tpService.deleteTraining(request.getParameter("id"));
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/getTrainings", method = RequestMethod.GET)
	public ResponseEntity<?> getTrainings() {
		// Request to get training's schedule
		List<CustomSchedule> schedules = tpService.retrieveTrainingCustomSchedules();
		
		return ResponseEntity.ok(schedules);
	}
	
	@RequestMapping(value = "/attendance/{id}")
	public String displayAttendance(ModelMap map, @PathVariable String id) {
		// Display table of users' attendance on a certain training
		TrainingPlan training = tpService.retrieveTraining(id);
			
		if(training != null) {
			List<User> participants = tpService.retrieveTrainingPeople(training, "Participant", false);
			List<Schedule> schedules = tpService.sortSchedule(training.getSchedules());
						
			map.addAttribute("participants", participants);
			map.addAttribute("schedules", schedules);	
			map.addAttribute("training", training);
		} else {
			return "redirect:/error/404";
		}
		
		return "/training/attendance";
	}
	
	@RequestMapping(value = "/attendance/timein", method = RequestMethod.POST)
	public ResponseEntity<?> attendanceTimeIn(HttpServletRequest request) {
		// Request to set Time In to user
		String trainingId = request.getParameter("training");
		String time = request.getParameter("time");
		String date = request.getParameter("date");
		String ids = request.getParameter("ids");
		
		boolean result = tpService.insertAttendance(trainingId, ids, time, date);
			
		return ResponseEntity.ok(result);	
	}
	
	@RequestMapping(value = "/attendance/timeout", method = RequestMethod.POST)
	public ResponseEntity<?> attendanceTimeOut(HttpServletRequest request) {
		// Request to set Time Out to user
		String trainingId = request.getParameter("training");
		String time = request.getParameter("time");	
		String date = request.getParameter("date");
		String ids = request.getParameter("ids");
		
		String result = tpService.insertTimeOutAttendance(trainingId, ids, time, date);
			
		return ResponseEntity.ok(result);	
	} 
	
	@RequestMapping(value = "/attendance/absent", method = RequestMethod.POST)
	public ResponseEntity<?> attendanceAbsent(HttpServletRequest request) {
		// Request to set Absent to user
		String trainingId = request.getParameter("training");
		String date = request.getParameter("date");
		String id = request.getParameter("id");
		
		boolean result = tpService.insertAbsentAttendance(trainingId, id, date);
			
		return ResponseEntity.ok(result);	
	}
	
	@RequestMapping(value = "/attendance/reset", method = RequestMethod.POST)
	public ResponseEntity<?> attendanceReset(HttpServletRequest request) {
		// Request to Reset attendance of user
		String trainingId = request.getParameter("training");
		String id = request.getParameter("id");
		String date = request.getParameter("date");
		
		boolean result = tpService.resetAttendance(trainingId, id, date);
			
		return ResponseEntity.ok(result);	
	}
	
}
