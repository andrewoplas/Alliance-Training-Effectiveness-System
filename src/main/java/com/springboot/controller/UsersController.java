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

import com.springboot.entities.Position;
import com.springboot.entities.User;
import com.springboot.entities.UserEvent;
import com.springboot.entities.custom.CustomUser;
import com.springboot.service.PositionService;
import com.springboot.service.TrainingPlanService;
import com.springboot.service.UsersService;

@Controller
@RequestMapping("/ates/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private TrainingPlanService tpService;
	
	
	@RequestMapping(value = "/list")
	public String displayUser(ModelMap map) {
		// Display table of approved users 
		List<CustomUser> users = usersService.retrieveUsers();
		
		map.addAttribute("users", users);
		return "/users/list";
	}
	
	@RequestMapping(value = "/view/{userID}")
	public String viewuser(ModelMap map, @PathVariable String userID) {
		// Display information of a certain user
		User user = usersService.retrieveUser(userID);
		
		if(user != null) {
			List<UserEvent> userEvents = tpService.retrieveUserEvent(user.getId());
			List<Position> positions = positionService.retrievePositions();
			
			map.addAttribute("admin", true);
			map.addAttribute("user", user);
			map.addAttribute("userEvents", userEvents);
			map.addAttribute("positions", positions);
		} else {
			return "redirect:/error/404";
		}	
			
		return "/users/view";
	}
	
	@RequestMapping(value = "/create")
	public String createUser(ModelMap map) {
		// Display form to create user
		List<Position> positions = positionService.retrievePositions();
		
		map.addAttribute("positions", positions);
		return "/users/create";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editUser(ModelMap map, @PathVariable String id) {
		// Display form to edit user
		List<Position> positions = positionService.retrievePositions();
		User user = usersService.retrieveUser(id);
		
		if(user != null) {
			map.addAttribute("positions", positions);
			map.addAttribute("user", user);
		} else {
			return "redirect:/error/404";
		}	
		
		return "/users/edit";
	}
	
	@RequestMapping(value = "/request")
	public String displayRequest(ModelMap map) {
		// Display table of users who requested to register or has pending status
		List<User> users = usersService.retrievePendingUsers();
		
		map.addAttribute("users", users);
		return "/users/request";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(HttpServletRequest request) {
		// Request to create user
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String position = request.getParameter("position");
		
		String result;
		try {
			result = usersService.insertUser(name, email, position);
		} catch (Exception ex) {
			return ResponseEntity.ok("error_exception");
		}
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<?> removeUser(HttpServletRequest request) {
		// Request to remove user
		String id = request.getParameter("id");
		
		boolean result = usersService.removeUser(id);
		
		return ResponseEntity.ok(result);
	}	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> editUser(HttpServletRequest request) {
		// Request to edit user
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String position = request.getParameter("position");
		String password = request.getParameter("password");
		
		String result;
		try {
			result = usersService.editUser(id, name, email, position, password);
		} catch (Exception ex) {
			return ResponseEntity.ok("error_exception");
		}
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/view/edit", method = RequestMethod.POST)
	public ResponseEntity<?> editUserByView(HttpServletRequest request) {
		// Request to edit user in the view page
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String password = request.getParameter("password");
		
		usersService.editUser(id, name, position, password);
		
		return ResponseEntity.ok(true);
	}
	
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public ResponseEntity<?> approve(HttpServletRequest request) {
		// Request to approve user (set status to 'approved')
		String id = request.getParameter("id");
		
		boolean result = usersService.approveUser(id);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/decline", method = RequestMethod.POST)
	public ResponseEntity<?> decline(HttpServletRequest request) {
		// Request to decline user (remove user)
		String id = request.getParameter("id");
		
		boolean result = usersService.declineUser(id);
		return ResponseEntity.ok(result);
	}
		
}