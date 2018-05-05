package com.springboot.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springboot.entities.Trainingplan;
@Repository
@Transactional
public class TrainingPlanRepository {

	public void addTrainingPlan(EntityManager em, Trainingplan tPlan) {
		em.persist(tPlan);
	}

	public List<Trainingplan> getTrainingPlan(EntityManager em) {
		StringBuilder trainingPlanQuery = new StringBuilder("FROM Trainingplan");
		Query query = em.createQuery(trainingPlanQuery.toString());
		List<Trainingplan> tPlanList = query.getResultList();
		return tPlanList;
	}

	public List<Trainingplan> getTrainingPlansByTitle(EntityManager em, String tTitle) {
		List<Trainingplan> tPlanList = null;
		StringBuilder trainingPlanQuery = new StringBuilder("FROM Trainingplan WHERE title LIKE :title");
		Query query = em.createQuery(trainingPlanQuery.toString());
		query.setParameter("title", "%" + tTitle + "%");
		
		tPlanList = query.getResultList();
		return tPlanList;
	}

	public Trainingplan getSingleTrainingPlanById(EntityManager em, String tId) {
		Trainingplan tPlan = null;
		int trainingId = Integer.parseInt(tId);
		StringBuilder trainingPlanQuery = new StringBuilder("FROM Trainingplan WHERE id = :id");
		Query query = em.createQuery(trainingPlanQuery.toString());
		query.setParameter("id", trainingId);
		
		tPlan = (Trainingplan) query.getSingleResult();
		return tPlan;
	}

	public Trainingplan updateTrainingPlan(EntityManager em,
			Trainingplan outdatedTPlan) {
		int id = outdatedTPlan.getId();
		em.merge(outdatedTPlan);
		
		StringBuilder trainingPlanQuery = new StringBuilder("FROM Trainingplan WHERE id = :id");
		Query query = em.createQuery(trainingPlanQuery.toString());
		query.setParameter("id", id);
		
		Trainingplan tPlan = (Trainingplan) query.getSingleResult();
		return tPlan;
	}

	public void removeTrainingPlansById(EntityManager em, String[] idsToDelete) {
		StringBuilder trainingPlanQuery = new StringBuilder("DELETE FROM Trainingplan WHERE id IN (:idsToDelete)");
		Query query = em.createQuery(trainingPlanQuery.toString());
		List<Integer> IdsToDelete = new ArrayList<>();
		for (String s: idsToDelete){
			IdsToDelete.add(Integer.parseInt(s));
		}
		query.setParameter("idsToDelete",IdsToDelete);
		query.executeUpdate();
	}

	public void removeTrainingPlanById(EntityManager em, String tId) {
		int tID = Integer.parseInt(tId);
		StringBuilder trainingPlanQuery = new StringBuilder("DELETE FROM Trainingplan WHERE id = :id");
		Query query = em.createQuery(trainingPlanQuery.toString());
		query.setParameter("id",tID);
		query.executeUpdate();
	}

}
