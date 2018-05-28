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
import com.springboot.entities.custom.CustomSchedule;
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
	
	@RequestMapping(value = "/invitation-status/{trainingPlanID}")
	public String invitationStatus(ModelMap map, @PathVariable String trainingPlanID) {
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
		TrainingPlan training = tpService.retrieveTraining(id);
		
		if(training != null) {
			List<User> participants = tpService.retrieveTrainingPeople(training, "Participant", true);
			List<User> internal = tpService.retrieveTrainingPeople(training, "Internal", true);
			List<User> external = tpService.retrieveTrainingPeople(training, "External", true);
			List<User> supervisors = tpService.retrieveTrainingPeople(training, "Supervisor", true);
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
	public String create(ModelMap map) {
		List<User> users = usersService.retrieveApprovedUsers();
		
		map.addAttribute("users", users);
		map.addAttribute("map", true);
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
		boolean result = tpService.editTraining(training);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTraining(HttpServletRequest request) {		
		boolean result = tpService.deleteTraining(request.getParameter("id"));
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/getTrainings", method = RequestMethod.GET)
	public ResponseEntity<?> getTrainings() {
		List<CustomSchedule> schedules = tpService.retrieveTrainingCustomSchedules();
		
		return ResponseEntity.ok(schedules);
	}
	
	@RequestMapping(value = "/attendance/{id}")
	public String attendance(ModelMap map, @PathVariable String id) {
		TrainingPlan training = tpService.retrieveTraining(id);
			
		if(training != null) {
			List<User> participants = tpService.retrieveTrainingPeople(training, "Participant", true);
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
		String trainingId = request.getParameter("training");
		String time = request.getParameter("time");
		String date = request.getParameter("date");
		String ids = request.getParameter("ids");
		
		tpService.insertAttendance(trainingId, ids, time, date);
			
		return ResponseEntity.ok(true);	
	}
	
	@RequestMapping(value = "/attendance/timeout", method = RequestMethod.POST)
	public ResponseEntity<?> attendanceTimeOut(HttpServletRequest request) {
		String trainingId = request.getParameter("training");
		String time = request.getParameter("time");	
		String date = request.getParameter("date");
		String ids = request.getParameter("ids");
		
		String result = tpService.insertTimeOutAttendance(trainingId, ids, time, date);
			
		return ResponseEntity.ok(result);	
	}
	
	@RequestMapping(value = "/attendance/absent", method = RequestMethod.POST)
	public ResponseEntity<?> attendanceAbsent(HttpServletRequest request) {
		String trainingId = request.getParameter("training");
		String date = request.getParameter("date");
		String id = request.getParameter("id");
		
		tpService.insertAbsentAttendance(trainingId, id, date);
			
		return ResponseEntity.ok(true);	
	}
	
	@RequestMapping(value = "/attendance/reset", method = RequestMethod.POST)
	public ResponseEntity<?> attendanceReset(HttpServletRequest request) {
		String trainingId = request.getParameter("training");
		String id = request.getParameter("id");
		String date = request.getParameter("date");
		
		tpService.resetAttendance(trainingId, id, date);
			
		return ResponseEntity.ok(true);	
	}
	
}
