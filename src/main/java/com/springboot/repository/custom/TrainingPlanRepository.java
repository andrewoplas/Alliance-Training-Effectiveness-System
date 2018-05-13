package com.springboot.repository.custom;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

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
	
	
}
