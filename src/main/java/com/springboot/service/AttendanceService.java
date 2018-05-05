package com.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.controller.Attendance;
import com.springboot.entities.Trainingplan;
import com.springboot.repository.custom.AttendanceRepository;

@Service
public class AttendanceService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private AttendanceRepository attendanceRepository;

	public void addAttendance(Attendance attendance) {
		attendanceRepository.addAttendance(em,attendance);
	}

	public List<Attendance> getAttendance() {
		return attendanceRepository.getAttendance(em);
	}

	public List<Attendance> getAttendanceByDate(String date) {
		return attendanceRepository.getAttendanceByDate(em,date);
	}

	public Attendance getSingleAttendanceById(String attendanceId) {
		return attendanceRepository.getSingleAttendanceById(em,attendanceId);
	}

	public Attendance updateAttendance(Attendance attendance,String attendanceId) {
		Attendance outdatedAttendance = attendanceRepository.getSingleTrainingPlanById(em,attendanceId);
		outdatedAttendance.setDate(attendance.getDate());
		outdatedAttendance.setTimeIn(attendance.getTimeIn());
		outdatedAttendance.setTimeOut(attendance.getTimeOut());
		outdatedAttendance.setUserId(attendance.getUserId());
		return attendanceRepository.updateAttendance(em,outdatedAttendance);
	}

	public void removeAttendanceById(String attendanceId) {
		attendanceRepository.removeAttendanceById(em,attendanceId);
	}
	
	
}
