package com.springboot.repository.custom;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springboot.entities.Attendance;
import com.springboot.entities.Schedule;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;


@Repository
@Transactional
public class TrainingPlanRepository {
	
	public int insertTraining(EntityManager em, TrainingPlan trainingPlan) {
		em.persist(trainingPlan);
		em.flush();
		
		return trainingPlan.getId();
	}
	
	public void insertSchedule(EntityManager em, Schedule[] schedules) {
		for(int i=0; i<schedules.length; i++) {
			em.persist(schedules[i]);
		}
	}
	
	public void insertUserEvent(EntityManager em, UserEvent[] userEvents) {
		for(int i=0; i<userEvents.length; i++) {
			em.persist(userEvents[i]);
		}
	}
	
	public boolean editTraining(EntityManager em, TrainingPlan trainingPlan) {
		TrainingPlan training = em.find(TrainingPlan.class, trainingPlan.getId());
		
		try {
			training.setTitle(trainingPlan.getTitle());
			training.setDescription(trainingPlan.getDescription());
			training.setCourseOutline(trainingPlan.getCourseOutline());
			
			return true;
		} catch (Exception ex ) {
			return false;
		}
	}
	
	public void editSchedule(EntityManager em, Schedule[] schedules, int id) {
		String sql = "DELETE FROM Schedule WHERE trainingPlanID = :trainingPlanID";
		Query query = em.createQuery(sql);
		query.setParameter("trainingPlanID", id);
		query.executeUpdate();
		
		for(int i=0; i<schedules.length; i++) {
			em.persist(schedules[i]);
		}
	}
	
	public void editUserEvent(EntityManager em, int id, UserEvent[] userEvents, Collection<Integer> userIDS) {		
		String sql = "DELETE FROM UserEvent WHERE trainingPlanID = :trainingID AND userID NOT IN (:userIds)";
		Query query = em.createQuery(sql.toString());
		query.setParameter("trainingID", id);
		query.setParameter("userIds", userIDS);
		query.executeUpdate();
		
		
		for(int i=0; i<userEvents.length; i++) {
			sql = "UPDATE UserEvent SET role = :newRole, status = :newStatus WHERE trainingPlanID = :tid AND userID = :uid AND role <> :role";
			query = em.createQuery(sql);
			query.setParameter("tid", id);
			query.setParameter("uid", userEvents[i].getUser().getId());
			query.setParameter("newRole", userEvents[i].getRole());
			query.setParameter("role", userEvents[i].getRole());
			query.setParameter("newStatus", "pending");
			query.executeUpdate();
			
			sql = "FROM UserEvent WHERE trainingPlanID = :tid AND userID = :uid";
			query = em.createQuery(sql);
			query.setParameter("tid", id);
			query.setParameter("uid", userEvents[i].getUser().getId());
			
			System.out.println("SELECT: " + id + " " + userEvents[i].getUser().getId() + " = " + query.getResultList().size());
			if(query.getResultList().size() == 0) {
				System.out.println(userEvents[i].getUser().getName());
				em.persist(userEvents[i]);
			}			
		}
	}

	public List<TrainingPlan> retrieveTrainings(EntityManager em) {
		List<TrainingPlan> trainings = null;
		StringBuilder sql = new StringBuilder("FROM TrainingPlan");
		Query query = em.createQuery(sql.toString());
		trainings = query.getResultList();		

		return trainings;
	}
	
	public TrainingPlan retrieveTraining(EntityManager em, int id) {
		TrainingPlan training = null;
		StringBuilder sql = new StringBuilder("FROM TrainingPlan WHERE id = :id");
		Query query = em.createQuery(sql.toString());
		query.setParameter("id", id);
		
		training = query.getResultList().size() != 1? null : (TrainingPlan)query.getResultList().get(0);		

		return training;
	}
	
	public boolean removeTraining(EntityManager em, int id) {
		TrainingPlan training = em.find(TrainingPlan.class, id);
		
		em.remove(training);
		return true;
	}

	public void insertAttendance(EntityManager em, List<Attendance> attendances) {
		Query query;
		
		for(Attendance attendance: attendances) {
			String sql = "UPDATE Attendance SET timeIn = :timeIn WHERE trainingPlanID = :tid AND userID = :uid AND date = :date";
			query = em.createQuery(sql);
			query.setParameter("tid", attendance.getTrainingPlan().getId());
			query.setParameter("uid", attendance.getUser().getId());
			query.setParameter("date", attendance.getDate());
			query.setParameter("timeIn", attendance.getTimeIn());
			int result = query.executeUpdate();
			
			if(result <= 0) {
				em.persist(attendance);
			}
		}
	}
	
	public boolean checkHasTimeIn(EntityManager em, Collection<Integer> ids, int trainingId, Date date) {
		Query query;
		
		String sql = "FROM Attendance WHERE date = :date AND trainingPlanID = :tid AND userID IN(:uid) AND timeIn != null";
		query = em.createQuery(sql);
		query.setParameter("tid", trainingId);
		query.setParameter("uid", ids);
		query.setParameter("date", date);
		return query.getResultList().size() == ids.size();
	}
	
	public void insertTimeOutAttendance(EntityManager em, List<Attendance> attendances) {
		Query query;
		
		for(Attendance attendance: attendances) {
			String sql = "UPDATE Attendance SET timeOut = :timeOut WHERE trainingPlanID = :tid AND userID = :uid AND date = :date";
			query = em.createQuery(sql);
			query.setParameter("tid", attendance.getTrainingPlan().getId());
			query.setParameter("uid", attendance.getUser().getId());
			query.setParameter("date", attendance.getDate());
			query.setParameter("timeOut", attendance.getTimeOut());
			query.executeUpdate();
		}
	}

	public void insertAbsentAttendance(EntityManager em, Attendance attendance) {
		String sql = "DELETE FROM Attendance WHERE trainingPlanID = :tid AND userID = :uid AND date = :date";
		Query query = em.createQuery(sql);
		query.setParameter("tid", attendance.getTrainingPlan().getId());
		query.setParameter("uid", attendance.getUser().getId());
		query.setParameter("date", attendance.getDate());
		query.executeUpdate();
		
		em.persist(attendance);
	}

	public void resetAttendance(EntityManager em, Attendance attendance) {
		Query query;
		String sql = "UPDATE Attendance SET timeIn = :timeIn, timeOut = :timeOut WHERE trainingPlanID = :tid AND userID = :uid AND date = :date";
		query = em.createQuery(sql);
		query.setParameter("tid", attendance.getTrainingPlan().getId());
		query.setParameter("uid", attendance.getUser().getId());
		query.setParameter("date", attendance.getDate());
		query.setParameter("timeIn", null);
		query.setParameter("timeOut", null);
		query.executeUpdate();
	}


}
