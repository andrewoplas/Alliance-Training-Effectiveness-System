package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.Trainingplan;
import com.springboot.service.UserService;

@Controller
@RequestMapping("/participant")
public class ParticipantController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String loadAddParticipant(){
		return "participant/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addParticipant(HttpServletRequest request, ModelMap map){
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		int isAdmin = Integer.parseInt(request.getParameter("isAdmin"));
		userService.addUser(name, position,email,password,status,isAdmin);
		return "participant/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getParticipant(ModelMap map){
		List<User> participantList = userService.getUser();
		map.addAttribute("resultParticipantList", participantList);
		return "participant/list";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public String loadGetParticipant(){
		return "participant/get";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.POST)
	public String getParticipantByName(HttpServletRequest request, ModelMap map){
		String name = request.getParameter("name");
		List<User> participantList = userService.getUserByName(name);
		map.addAttribute("resultParticipantList", participantList);
		return "participant/get";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String loadUpdateParticipant(HttpServletRequest request, ModelMap map){
		String uid = request.getParameter("id");
		User participant = userService.getSingleUserById(uid);
		map.addAttribute("participant",participant);
		return "participant/update";
	}
	
	@RequestMapping(value="/updating",method=RequestMethod.POST)
	public String UpdateParticipant(HttpServletRequest request, ModelMap map){
		String uid = request.getParameter("id");
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		int isAdmin = Integer.parseInt(request.getParameter("isAdmin"));
		
		User participant = new User();
		participant.setName(name);
		participant.setPosition(position);
		participant.setEmail(email);
		participant.setPassword(password);
		participant.setStatus(status);
		participant.setIsAdmin(isAdmin);
		participant = userService.updateUser(participant,uid);
		map.addAttribute("participant", participant);

		return getParticipant(map);
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String RemoveParticipant(HttpServletRequest request, ModelMap map){
		String uid = request.getParameter("id");
		userService.removeUserById(uid);
		return getParticipant(map);
	}
}
