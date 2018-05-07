package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/trainingplan")
public class TrainingPlanController {

	@RequestMapping(value = "/description", method = RequestMethod.GET)
	public String  showCourseOutline(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		
		

		return "CourseOutline";
	}
	
	@RequestMapping(value = "/descriptionSave", method = RequestMethod.POST)
	public String  SaveCourseOutline(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		
		String courseOutline = request.getParameter("serialize");
		System.out.println(courseOutline);

		return "CourseOutline";
	}
	
	



}
