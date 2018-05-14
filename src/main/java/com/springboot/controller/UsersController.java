package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.Position;
import com.springboot.entities.User;
import com.springboot.entities.custom.CustomUser;
import com.springboot.service.PositionService;
import com.springboot.service.UsersService;

@Controller
@RequestMapping("/ates/users")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	PositionService positionService;
		
	@RequestMapping(value = "/list")
	public String index(ModelMap map) {
		List<CustomUser> users = usersService.retrieveUsers();
		
		map.addAttribute("users", users);
		return "/users/list";
	}
	
	@RequestMapping(value = "/create")
	public String create(ModelMap map) {
		List<Position> positions = positionService.retrievePositions();
		
		map.addAttribute("positions", positions);
		return "/users/create";
	}
	
	@RequestMapping(value = "/request")
	public String request(ModelMap map) {
		List<User> users = usersService.retrievePendingUsers();
		
		map.addAttribute("users", users);
		return "/users/request";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity create(HttpServletRequest request) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String position = request.getParameter("position");
		
		String result = usersService.insertUser(name, email, position);	
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = {"/remove"}, method = RequestMethod.POST)
	public ResponseEntity remove(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		usersService.removeUser(id);
		return ResponseEntity.ok(id);
	}	
	
	@RequestMapping(value = {"/approve"}, method = RequestMethod.POST)
	public ResponseEntity<?> approve(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		usersService.approveUser(id);
		return ResponseEntity.ok(id);
	}
	
	@RequestMapping(value = {"/decline"}, method = RequestMethod.POST)
	public ResponseEntity<?> decline(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		usersService.declineUser(id);
		return ResponseEntity.ok(id);
	}
		
}