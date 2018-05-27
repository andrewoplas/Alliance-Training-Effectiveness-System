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

import com.springboot.entities.FormAnswer;
import com.springboot.entities.FormAssignment;
import com.springboot.entities.SaAnswer;
import com.springboot.entities.SaAssignment;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;


@Repository
@Transactional
public class UserFormsRepository {
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
				
		return query.list();
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

	public void insertAnswer(EntityManager em, SaAnswer answer) {
		em.persist(answer);
	}

	public void updateSAAssignmentToAnswered(EntityManager em, int assignmentID) {
		String sql = "UPDATE SaAssignment SET status = :status WHERE id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("status", "answered");
		query.setParameter("id", assignmentID);
		query.executeUpdate();
	}

	public FormAssignment retrieveFormAssignmentById(EntityManager em, int assignmentID) {
		return em.find(FormAssignment.class, assignmentID);
	}

	public void insertAnswer(EntityManager em, FormAnswer formAnswer) {
		em.persist(formAnswer);
	}

	public void updateAssignmentToAnswered(EntityManager em, int assignmentID) {
		String sql = "UPDATE FormAssignment SET status = :status WHERE id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("status", "answered");
		query.setParameter("id", assignmentID);
		query.executeUpdate();
	}

}
