package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.User;
import com.springboot.service.UsersService;


@Controller
@RequestMapping("/ates/training")
public class TrainingPlanController {
	
	@Autowired
	UsersService usersService;
	
	@RequestMapping(value = { "", "/" })
	public String index() {
		return "pages/trainingPlan";
	}
	
	@RequestMapping(value = "/create")
	public String create(ModelMap map) {
		List<User> users = usersService.retrieveApprovedUsers();
		
		map.addAttribute("users", users);
		return "pages/trainingPlan";
	}

	@RequestMapping(value = "/descriptionSave", method = RequestMethod.POST)
	public String  SaveCourseOutline(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		
		String courseOutline = request.getParameter("serialize");
		System.out.println(courseOutline);

		return "CourseOutline";
	}
}
