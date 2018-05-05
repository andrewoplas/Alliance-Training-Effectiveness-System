package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springboot.entities.Trainingplan;
import com.springboot.service.Attendance;

@Repository
@Transactional
public class AttendanceRepository {

	public void addAttendance(EntityManager em, Attendance attendance) {
		em.persist(Attendance attendance);
	}

	public List<Attendance> getAttendance(EntityManager em) {
		StringBuilder attendanceQuery = new StringBuilder("FROM Attendance");
		Query query = em.createQuery(attendanceQuery.toString());
		List<Attendance> attendanceList = query.getResultList();
		return attendanceList;
	}

	public List<Attendance> getAttendanceByDate(EntityManager em, String date) {
		StringBuilder attendanceQuery = new StringBuilder("FROM Attendance WHERE date = :date");
		Query query = em.createQuery(attendanceQuery.toString());
		query.setParameter("date", date);

		List<Attendance> attendanceList = query.getResultList();
		return attendanceList;
	}

	public Attendance getSingleAttendanceById(EntityManager em,
			String attendanceId) {
		int attendanceID = Integer.parseInt(attendanceId);
		StringBuilder attendanceQuery = new StringBuilder("FROM Attendance WHERE id = :id");
		Query query = em.createQuery(attendanceQuery.toString());
		query.setParameter("id", attendanceID);
		
		Attendance attendance = (Attendance) query.getSingleResult();
		return attendance;
	}

	public Attendance updateAttendance(EntityManager em,Attendance outdatedAttendance) {
		int id = outdatedAttendance.getId();
		em.merge(outdatedAttendance);
		
		StringBuilder attendanceQuery = new StringBuilder("FROM Attendance WHERE id = :id");
		Query query = em.createQuery(attendanceQuery.toString());
		query.setParameter("id", id);
		
		Attendance attendance = (Attendance) query.getSingleResult();
		return attendance;
	}

	public void removeAttendanceById(EntityManager em, String attendanceId) {
		int attendanceID = Integer.parseInt(attendanceId);
		StringBuilder attendanceQuery = new StringBuilder("DELETE FROM Attendance WHERE id = :id");
		Query query = em.createQuery(attendanceQuery.toString());
		query.setParameter("id",attendanceID);
		query.executeUpdate();
	}

}
