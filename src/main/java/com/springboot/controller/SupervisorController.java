package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.service.UserService;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String loadAddSupervisor(){
		return "supervisor/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addSupervisor(HttpServletRequest request, ModelMap map){
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		int isAdmin = Integer.parseInt(request.getParameter("isAdmin"));
		userService.addUser(name, position,email,password,status,isAdmin);
		return "supervisor/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getSupervisor(ModelMap map){
		List<User> supervisorList = userService.getUser();
		map.addAttribute("resultSupervisorList", supervisorList);
		return "supervisor/list";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public String loadGetSupervisor(){
		return "supervisor/get";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.POST)
	public String getSupervisorByName(HttpServletRequest request, ModelMap map){
		String name = request.getParameter("name");
		List<User> supervisorList = userService.getUserByName(name);
		map.addAttribute("resultSupervisorList", supervisorList);
		return "supervisor/get";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String loadUpdateSupervisor(HttpServletRequest request, ModelMap map){
		String uid = request.getParameter("id");
		User supervisor = userService.getSingleUserById(uid);
		map.addAttribute("supervisor",supervisor);
		return "supervisor/update";
	}
	
	@RequestMapping(value="/updating",method=RequestMethod.POST)
	public String UpdateSupervisor(HttpServletRequest request, ModelMap map){
		String uid = request.getParameter("id");
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		int isAdmin = Integer.parseInt(request.getParameter("isAdmin"));
		
		User supervisor = new User();
		supervisor.setName(name);
		supervisor.setPosition(position);
		supervisor.setEmail(email);
		supervisor.setPassword(password);
		supervisor.setStatus(status);
		supervisor.setIsAdmin(isAdmin);
		supervisor = userService.updateUser(supervisor,uid);
		map.addAttribute("supervisor", supervisor);

		return getSupervisor(map);
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String RemoveSupervisor(HttpServletRequest request, ModelMap map){
		String uid = request.getParameter("id");
		userService.removeUserById(uid);
		return getSupervisor(map);
	}
}
