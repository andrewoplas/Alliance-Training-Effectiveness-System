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
import com.springboot.service.UsersService;

@Controller
@RequestMapping("/ates/users")
public class UsersController {
	@Autowired
	UsersService usersService;
		
	@RequestMapping(value = {"", "/"})
	public String index() {
		return "pages/users";
	}
	
	@RequestMapping(value = {"/request"})
	public String request(ModelMap map) {
		List<User> users = usersService.retrievePendingUser();
		
		map.addAttribute("users", users);
		return "pages/usersRequest";
	}
	
	@RequestMapping(value = {"/approve"}, method = RequestMethod.POST)
	public ResponseEntity<?> approve(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		usersService.approveUser(id);
		return ResponseEntity.ok(id);
	}
	
	@RequestMapping(value = {"/decline"})
	public ResponseEntity<?> decline(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		usersService.declineUser(id);
		return ResponseEntity.ok(id);
	}
}