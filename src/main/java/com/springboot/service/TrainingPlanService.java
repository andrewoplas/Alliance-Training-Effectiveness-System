package com.springboot.service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.body.EventDay;
import com.springboot.body.Training;
import com.springboot.entities.Schedule;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;
import com.springboot.repository.custom.TrainingPlanRepository;

@Service
public class TrainingPlanService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private TrainingPlanRepository tpRepository;
	
	public boolean insertTraining(Training training) {
		boolean result = false;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		
		// Create training
		TrainingPlan tp = new TrainingPlan();
		tp.setTitle(training.getTitle());
		tp.setDescription(training.getDescription());
		tp.setCourseOutline(training.getCourseOutline());
		
		int tpId = tpRepository.insertTraining(em, tp);
		tp.setId(tpId);
		
		// Save schedule
		EventDay[] eventDays = training.getCalendar();
		Schedule[] schedule = new Schedule[eventDays.length];
		
		try {
			for(int i=0; i<eventDays.length; i++) {
				schedule[i] = new Schedule();
				schedule[i].setTrainingPlan(tp);
				schedule[i].setDate(dateFormatter.parse(eventDays[i].getDate()));
				schedule[i].setTimeStart(new Time(timeFormatter.parse(eventDays[i].getStartTime()).getTime()));
				schedule[i].setTimeEnd(new Time(timeFormatter.parse(eventDays[i].getEndTime()).getTime()));
			}
			
			tpRepository.insertSchedule(em, schedule);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		// Save User involved
		String[] supervisors = training.getSupervisors();
		String[] facilitators = training.getFacilitators();
		String[] participants = training.getParticipants();
		ArrayList<UserEvent> userEvents = new ArrayList<UserEvent>();
		
		try {
			for(int i=0; i<supervisors.length; i++) {
				UserEvent user = new UserEvent();
				user.setUserID(Integer.parseInt(supervisors[i]));
				user.setTrainingPlan(tp);
				user.setRole("Supervisor");
				user.setStatus("pending");
				
				userEvents.add(user);
			}
			
			for(int i=0; i<facilitators.length; i++) {
				UserEvent user = new UserEvent();
				user.setUserID(Integer.parseInt(facilitators[i]));
				user.setTrainingPlan(tp);
				user.setRole("Facilitator Internal");
				user.setStatus("pending");
				
				userEvents.add(user);
			}
			
			for(int i=0; i<participants.length; i++) {
				UserEvent user = new UserEvent();
				user.setUserID(Integer.parseInt(participants[i]));
				user.setTrainingPlan(tp);
				user.setRole("Participant");
				user.setStatus("pending");
				
				userEvents.add(user);
			}
			
			tpRepository.insertUserEvent(em, userEvents.toArray(new UserEvent[userEvents.size()]));
			result = true;
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	

}

