package com.springboot.repository.custom;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.springboot.entities.SaAnswer;
import com.springboot.entities.SaAssignment;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;


@Repository
@Transactional
public class UserTrainingRepository {
	
	public boolean editTraining(EntityManager em, TrainingPlan trainingPlan) {
		TrainingPlan training = em.find(TrainingPlan.class, trainingPlan.getId());
		
		try {
			training.setDescription(trainingPlan.getDescription());
			training.setCourseOutline(trainingPlan.getCourseOutline());
			
			return true;
		} catch (Exception ex ) {
			return false;
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
	
	public List<UserEvent> retrieveUserEvent(EntityManager em, int userID) {
		List<UserEvent> userEvents = null;
		StringBuilder sql = new StringBuilder("FROM UserEvent WHERE userID = :id AND status <> :status");
		Query query = em.createQuery(sql.toString());
		query.setParameter("id", userID);
		query.setParameter("status", "pending");
		
		userEvents = query.getResultList();		

		return userEvents;
	}
	
	public List<UserEvent> retrievePendingUserEvent(EntityManager em, int userID) {
		List<UserEvent> userEvents = null;
		StringBuilder sql = new StringBuilder("FROM UserEvent WHERE userID = :id AND status = :status");
		Query query = em.createQuery(sql.toString());
		query.setParameter("id", userID);
		query.setParameter("status", "pending");
		
		userEvents = query.getResultList();		

		return userEvents;
	}

	public void acceptInvitation(EntityManager em, int id) {
		UserEvent userEvent = em.find(UserEvent.class, id);
		
		userEvent.setStatus("approved");		
	}
	
	public void declineInvitation(EntityManager em, int id) {
		UserEvent userEvent = em.find(UserEvent.class, id);
		
		userEvent.setStatus("declined");		
	}

	public UserEvent retrieveUserEventById(EntityManager em, int id) {
		StringBuilder sql = new StringBuilder("FROM UserEvent WHERE id = :id");
		Query query = em.createQuery(sql.toString());
		query.setParameter("id", id);
		query.setMaxResults(1);
		
		UserEvent userEvent = null;
		try {
			userEvent = (UserEvent) query.getSingleResult();
		} catch (Exception ex) { ex.printStackTrace(); }
		
		return userEvent;
	}
}
