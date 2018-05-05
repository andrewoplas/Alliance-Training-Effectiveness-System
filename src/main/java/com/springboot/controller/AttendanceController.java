package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.Trainingplan;
import com.springboot.service.AttendanceService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	
	@Autowired
	AttendanceService attendanceService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String loadAddAttendance(){
		return "/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addAttendance(HttpServletRequest request, ModelMap map){
		String date = request.getParameter("date");
		String timeIn = request.getParameter("timeIn");
		String timeOut = request.getParameter("timeOut");
		String userId = request.getParameter("userId");
		//Attendance attendance = new Attendance();
		//attendanceService.addAttendance(attendance);
		return "/add";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getAttendance(ModelMap map){
		List<Attendance> attendanceList = attendanceService.getAttendance();
		map.addAttribute("resultAttendance", attendanceList);
		return "attendance/list";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public String loadGetAttendanceByDate(){
		return "attendance/get";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.POST)
	public String loadGetAttendanceByDate(HttpServletRequest request, ModelMap map){
		String date = request.getParameter("date");
		List<Attendance> attendanceList = attendanceService.getAttendanceByDate(date);
		map.addAttribute("resultAttendanceList", attendanceList);
		return "trainingplan/get";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String loadUpdateAttendance(HttpServletRequest request, ModelMap map){
		String attendanceId = request.getParameter("attendanceId");
		Attendance attendance = attendanceService.getSingleAttendanceById(attendanceId);
		map.addAttribute("attendance",attendance);
		return "trainingplan/update";
	}
	
	@RequestMapping(value="/updating",method=RequestMethod.POST)
	public String updateAttendance(HttpServletRequest request, ModelMap map){
		String date = request.getParameter("date");
		String timeIn = request.getParameter("timeIn");
		String timeOut = request.getParameter("timeOut");
		int attendanceId = Integer.parseInt(request.getParameter("attendanceId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		Attendance attendance = new Attendance();
		attendance.setDate(date);
		attendance.setTimeIn(timeIn);
		attendance.setTimeOut(timeOut);
		attendance.setUserId(userId);
		attendance = attendanceService.updateAttendance(attendance,attendanceId);
		map.addAttribute("attendance", attendance);

		return getAttendance(map);
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String removeAttendance(HttpServletRequest request, ModelMap map){
		String attendanceId = request.getParameter("id");
		attendanceService.removeAttendanceById(attendanceId);
		return getAttendance(map);
	}
	
}
