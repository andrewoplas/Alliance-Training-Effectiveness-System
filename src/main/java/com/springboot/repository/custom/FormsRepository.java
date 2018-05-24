package com.springboot.repository.custom;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springboot.entities.SaCategory;
import com.springboot.entities.Schedule;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;


@Repository
@Transactional
public class FormsRepository {
	
	public void insertSkillsAssessment(EntityManager em, List<SaCategory> categories, Collection<Integer> categoryIDS) {
		if(categoryIDS.size() == 0) {
			categoryIDS.add(0);
		}
		
		String sql = "DELETE FROM SaCategory WHERE id NOT IN (:categoryIDS) SORT BY ";
		Query query = em.createQuery(sql.toString());
		query.setParameter("categoryIDS", categoryIDS);
		query.executeUpdate();
		
		for(SaCategory category : categories) {

			if(em.find(SaCategory.class, category.getId()) != null)
				em.merge(category); 
			else {
				category.setId(0);
				em.persist(category);
			}
			
			em.flush();
			
			if(!category.getSaCategories().isEmpty()) {
				insertChildSA(em, category.getSaCategories(), category);
			}
		}
	}
	
	public void insertChildSA(EntityManager em, List<SaCategory> categories, SaCategory parent) {
		for(SaCategory category : categories) {
			category.setSaCategory(parent);
			
			if(em.find(SaCategory.class, category.getId()) != null)
				em.merge(category); 
			else {
				category.setId(0);
				em.persist(category);
			}
			
			em.flush();
			
			if(!category.getSaCategories().isEmpty()) {
				insertChildSA(em, category.getSaCategories(), category);
			}
		}
	}
	
	public List<SaCategory> retrieveParentSkillsAssessment(EntityManager em) {
		List<SaCategory> parents = null;
		StringBuilder sql = new StringBuilder("FROM SaCategory WHERE parentID = NULL ORDER BY row_order ASC");
		Query query = em.createQuery(sql.toString());
		parents = query.getResultList();		

		return parents;
	}
	
}
