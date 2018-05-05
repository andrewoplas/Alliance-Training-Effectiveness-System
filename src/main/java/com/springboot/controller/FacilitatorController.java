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
@RequestMapping("/facilitator")
public class FacilitatorController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String loadAddFacilitator(){
		return "facilitator/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addFacilitator(HttpServletRequest request, ModelMap map){
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		int isAdmin = Integer.parseInt(request.getParameter("isAdmin"));
		userService.addUser(name, position,email,password,status,isAdmin);
		return "facilitator/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getFacilitator(ModelMap map){
		List<User> facilitatorList = userService.getUser();
		map.addAttribute("resultFacilitatorList", facilitatorList);
		return "facilitator/list";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public String loadGetFacilitator(){
		return "facilitator/get";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.POST)
	public String getFacilitatorByName(HttpServletRequest request, ModelMap map){
		String name = request.getParameter("name");
		List<User> facilitatorList = userService.getUserByName(name);
		map.addAttribute("resultFacilitatorList", facilitatorList);
		return "facilitator/get";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String loadUpdateFacilitator(HttpServletRequest request, ModelMap map){
		String uid = request.getParameter("id");
		User facilitator = userService.getSingleUserById(uid);
		map.addAttribute("facilitator",facilitator);
		return "facilitator/update";
	}
	
	@RequestMapping(value="/updating",method=RequestMethod.POST)
	public String UpdateFacilitator(HttpServletRequest request, ModelMap map){
		String uid = request.getParameter("id");
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		int isAdmin = Integer.parseInt(request.getParameter("isAdmin"));
		
		User facilitator = new User();
		facilitator.setName(name);
		facilitator.setPosition(position);
		facilitator.setEmail(email);
		facilitator.setPassword(password);
		facilitator.setStatus(status);
		facilitator.setIsAdmin(isAdmin);
		facilitator = userService.updateUser(facilitator,uid);
		map.addAttribute("facilitator", facilitator);

		return getFacilitator(map);
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String RemoveFacilitator(HttpServletRequest request, ModelMap map){
		String uid = request.getParameter("id");
		userService.removeUserById(uid);
		return getFacilitator(map);
	}
}
