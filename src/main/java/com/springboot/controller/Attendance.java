package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
@RequestMapping("ates/attendance")
public class Attendance {
	
	static String TrainingID;

		@RequestMapping(value = {"/load"}, method=RequestMethod.GET)
		public String index(HttpServletRequest request, ModelMap map) {
		TrainingID=request.getParameter("trainingID");
		map.addAttribute("trainingID", "100");
		return "pages/summary";
	}
		
		@RequestMapping(value = {"/attendanceCheck"}, method=RequestMethod.POST)
		public String SaveAttendance(HttpServletRequest request, ModelMap map) {
			
			
			
			
			String[] time =request.getParameterValues("timeIn");
			String[] id = request.getParameterValues("id");
			
			for(int counter=0;counter<time.length;counter++)
			{
			System.out.println( "TIME : " + time[counter] + "ID : " + id[counter]);
			}
			
			//System.out.println("TRAINING ID!! : " +  tID);
			
		
		return "pages/summary";
	}
}
