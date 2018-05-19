package com.springboot.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Schedule;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;
import com.springboot.entities.custom.CustomSchedule;
import com.springboot.repository.custom.UserTrainingRepository;

@Service
public class UserTrainingService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserTrainingRepository tpRepository;
	
	public List<UserEvent> retrieveUserEvent(int userID) {
		return tpRepository.retrieveUserEvent(em, userID) ;
	}
	
	public List<UserEvent> retrievePendingUserEvent(int userID) {
		return tpRepository.retrievePendingUserEvent(em, userID) ;
	}
	
	public void acceptInvitation(String id) {
		tpRepository.acceptInvitation(em, Integer.parseInt(id));
	}
	
	public void declineInvitation(String id) {
		tpRepository.declineInvitation(em, Integer.parseInt(id));
	}
	
	public List<CustomSchedule> retrieveUserTrainingCustomSchedules(int id) {
		List<UserEvent> userEvents = tpRepository.retrieveUserEvent(em, id);
		
		if(userEvents.size() > 0) {						
			List<CustomSchedule> schedules = new ArrayList<CustomSchedule>();
			DateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd");
			DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
			
			for(UserEvent userEvent: userEvents) {
				for(Schedule schedule : userEvent.getTrainingPlan().getSchedules()) {
					CustomSchedule cs = new CustomSchedule();
					cs.setId(userEvent.getTrainingPlan().getId());
					cs.setTitle(userEvent.getTrainingPlan().getTitle());
					cs.setDate(dateFormatter.format(schedule.getDate()));
					cs.setTimeStart(timeFormatter.format(schedule.getTimeStart()));
					cs.setTimeEnd(timeFormatter.format(schedule.getTimeEnd()));
					cs.setClassName(schedule.getColor());
					
					schedules.add(cs);
				}
			}
			
			return schedules;
		}
		
		return null;
	}
}