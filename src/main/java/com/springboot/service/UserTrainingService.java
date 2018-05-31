package com.springboot.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.body.AnswerSA;
import com.springboot.body.AssignmentSA;
import com.springboot.entities.SaAnswer;
import com.springboot.entities.SaAssignment;
import com.springboot.entities.SaCategory;
import com.springboot.entities.Schedule;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.User;
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
	
	public boolean acceptInvitation(String id) {
		try {
			tpRepository.acceptInvitation(em, Integer.parseInt(id));
			
			return true;
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			
			return false;
		}
	}
	
	public boolean declineInvitation(String id) {
		try {
			tpRepository.declineInvitation(em, Integer.parseInt(id));
			
			return true;
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			
			return false;
		}
	}
	
	public List<CustomSchedule> retrieveUserTrainingCustomSchedules(int id) {
		List<UserEvent> userEvents = tpRepository.retrieveUserEvent(em, id);
		
		// Get schedules of user' UserEvents
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

	public boolean checkTrainingInvolvement(User user, String id) {
		try {
			UserEvent userEvent = tpRepository.retrieveUserEventById(em, Integer.parseInt(id));
			boolean result = false;
			
			if(userEvent != null && userEvent.getUser().getId() == user.getId()) {
				result = true;
			}
			
			return result;
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			
			return false;
		}
	}

	public boolean checkTrainingInvolvementAndFacilitator(User user, String id) {
		try {
			UserEvent userEvent = tpRepository.retrieveUserEventById(em, Integer.parseInt(id));
			boolean result = false;
			
			if( userEvent != null && 
				userEvent.getUser().getId() == user.getId() &&
				userEvent.getRole().contains("Facilitator") ) {
					result = true;
			}
			
			return result;
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			
			return false;
		}
	}
	
	public boolean checkTrainingInvolvementAndSupervisor(User user, String id) {
		try { 
			UserEvent userEvent = tpRepository.retrieveUserEventById(em, Integer.parseInt(id));
			boolean result = false;
			
			if( userEvent != null && 
				userEvent.getUser().getId() == user.getId() &&
				userEvent.getRole().contains("Supervisor") ) {
					result = true;
			}
			
			return result;
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			
			return false;
		}
	}

	public boolean editTraining(String id, String description, String courseOutline) {
		try {
			TrainingPlan trainingPlan = new TrainingPlan();
			trainingPlan.setId(Integer.parseInt(id));
			trainingPlan.setDescription(description);
			trainingPlan.setCourseOutline(courseOutline);
			tpRepository.editTraining(em, trainingPlan);
			
			return true;
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			
			return false;
		}
		
	}
}