package com.springboot.repository.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springboot.entities.Position;
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
	
	public void editTraining(EntityManager em, TrainingPlan trainingPlan) {
		em.merge(trainingPlan);
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
	
	
	
	
}
