package com.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Trainingplan;
import com.springboot.repository.custom.TrainingPlanRepository;

@Service
public class TrainingPlanService {
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private TrainingPlanRepository trainingPlanRepository;
	
	public void addTrainingPlan(String title, String description, int schedId) {
		Trainingplan tPlan = new Trainingplan();
		tPlan.setTitle(title);
		tPlan.setDescription(description);
		tPlan.setSid(schedId);
		trainingPlanRepository.addTrainingPlan(em,tPlan);
	}
	
	public List<Trainingplan> getTrainingPlan(){
		return trainingPlanRepository.getTrainingPlan(em);
	}
		
	public List<Trainingplan> getTrainingPlanByTitle(String tTitle) {
		return trainingPlanRepository.getTrainingPlansByTitle(em,tTitle);
	}
	
	public Trainingplan getSingleTrainingPlanById(String tId) {
		return trainingPlanRepository.getSingleTrainingPlanById(em,tId);
	}
	
	public Trainingplan updateTrainingPlan(Trainingplan tPlan, String tId) {
		Trainingplan outdatedTPlan = trainingPlanRepository.getSingleTrainingPlanById(em,tId);
		outdatedTPlan.setTitle(tPlan.getTitle());
		outdatedTPlan.setDescription(tPlan.getDescription());
		outdatedTPlan.setSid(tPlan.getSid());
		return trainingPlanRepository.updateTrainingPlan(em,outdatedTPlan);
	}
	
	public void removeTrainingPlansById(String[] idsToDelete) {
		trainingPlanRepository.removeTrainingPlansById(em,idsToDelete);
	}
	
	public void removeTrainingPlanById(String tId) {
		trainingPlanRepository.removeTrainingPlanById(em,tId);
		
	}

}
