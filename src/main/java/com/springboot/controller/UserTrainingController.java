package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.User;
import com.springboot.entities.UserEvent;
import com.springboot.service.UserTrainingService;


@Controller
@RequestMapping("/ates/general")
public class UserTrainingController {
	
	@Autowired
	UserTrainingService tpService;
	
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
	
}
