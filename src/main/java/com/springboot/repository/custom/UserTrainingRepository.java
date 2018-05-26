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

	public void insertAssignment(EntityManager em, List<SaAssignment> assignments, Map<Integer, List<Integer>> pairs) {
			Iterator it = pairs.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				
				String sql = "DELETE FROM SaAssignment WHERE assignedTo = :assignedTo AND assigned NOT IN (:assigned)";
				Query query = em.createQuery(sql);
				query.setParameter("assignedTo", pair.getKey());
				query.setParameter("assigned", (Collection<Integer>)pair.getValue());
				query.executeUpdate();	
				
				it.remove(); // avoids a ConcurrentModificationException
			}			
		
			for(SaAssignment assignment : assignments) {
				String sql = "FROM SaAssignment WHERE assigned = :assigned AND assignedTo = :assignedTo";
				Query query = em.createQuery(sql);
				query.setParameter("assigned", assignment.getUserEvent1().getId());
				query.setParameter("assignedTo", assignment.getUserEvent2().getId());
				
				if(query.getResultList() == null || query.getResultList().size() == 0) {
					System.out.println(assignment.getUserEvent1().getId());
					em.persist(assignment);
				}
			}
	}

	public List<SaAssignment> retrieveAssignments(EntityManager em, int trainingID) {
		Session session = em.unwrap(Session.class);		
		StringBuilder stringQuery = new StringBuilder("SELECT a.id as id, a.type as type, a.assigned as assigned, a.assignedTo as assignedTo FROM sa_assignment a LEFT JOIN user_event e ON a.assignedTo = e.id WHERE e.trainingPlanID = :trainingID");
		SQLQuery query = session.createSQLQuery(stringQuery.toString());
		query.setParameter("trainingID", trainingID);
		query.addScalar("id");
		query.addScalar("type");
		query.addScalar("assigned");
		query.addScalar("assignedTo");
		query.setResultTransformer(Transformers.aliasToBean(SaAssignment.class));
				
		return (List<SaAssignment>)query.list();
	}

	public List<SaAssignment> retrieveAssignmentAssigned(EntityManager em, int userEventID) {
		List<SaAssignment> assignments = null;
		StringBuilder sql = new StringBuilder("FROM SaAssignment WHERE assigned = :userEventID");
		Query query = em.createQuery(sql.toString());
		query.setParameter("userEventID", userEventID);
		
		assignments = query.getResultList();		

		return assignments;
	}

	public SaAssignment retrieveAssignment(EntityManager em, int assignmentID) {
		SaAssignment assignment = em.find(SaAssignment.class, assignmentID);
		return assignment;
	}

	public void insertAnswer(EntityManager em, List<SaAnswer> answers, SaAssignment assignment) {
		for(SaAnswer answer : answers) {
			em.persist(answer);
		}
		
		String sql = "UPDATE SaAssignment SET status : status WHERE id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("status", "answered");
		query.executeUpdate();
	}
}
