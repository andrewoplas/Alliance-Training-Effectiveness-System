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

	public boolean checkTrainingInvolvement(User user, int id) {
		UserEvent userEvent = tpRepository.retrieveUserEventById(em, id);
		boolean result = false;
		
		if(userEvent != null && userEvent.getUser().getId() == user.getId()) {
			result = true;
		}
		
		return result;
	}

	public boolean checkTrainingInvolvementAndFacilitator(User user, int id) {
		UserEvent userEvent = tpRepository.retrieveUserEventById(em, id);
		boolean result = false;
		
		if( userEvent != null && 
			userEvent.getUser().getId() == user.getId() &&
			userEvent.getRole().contains("Facilitator") ) {
				result = true;
		}
		
		return result;
	}
	
	public boolean checkTrainingInvolvementAndSupervisor(User user, int id) {
		UserEvent userEvent = tpRepository.retrieveUserEventById(em, id);
		boolean result = false;
		
		if( userEvent != null && 
			userEvent.getUser().getId() == user.getId() &&
			userEvent.getRole().contains("Supervisor") ) {
				result = true;
		}
		
		return result;
	}

	public void editTraining(String id, String description, String courseOutline) {
		TrainingPlan trainingPlan = new TrainingPlan();
		trainingPlan.setId(Integer.parseInt(id));
		trainingPlan.setDescription(description);
		trainingPlan.setCourseOutline(courseOutline);
		tpRepository.editTraining(em, trainingPlan);
	}
	
	public Map<Integer, List<Integer>> retrieveAssignments(int trainingID) {
		List<SaAssignment> assignments = tpRepository.retrieveAssignments(em, trainingID);
		
		Map<Integer, List<Integer>> pairs = new HashMap<Integer, List<Integer>>();
		for(SaAssignment assignment : assignments) {
			if(pairs.containsKey(assignment.getAssignedTo())) {
				pairs.get(assignment.getAssignedTo()).add(assignment.getAssigned());
			} else {
				pairs.put(assignment.getAssignedTo(), new ArrayList<Integer>());
				pairs.get(assignment.getAssignedTo()).add(assignment.getAssigned());
			}
		}		
		
		return pairs;
	}

	public void insertAssignment(AssignmentSA[] assessments, int parseInt) {
		List<SaAssignment> assignments = new ArrayList<SaAssignment>();
		Map<Integer, List<Integer>> pairs = new HashMap<Integer, List<Integer>>();
		
		for(AssignmentSA assessment : assessments) {
			int user = assessment.getUser();
			List<Integer> assigned = new ArrayList<Integer>();
			
			
			// Self
			SaAssignment self = new SaAssignment();
			self.setUserEvent1(new UserEvent(user));
			self.setUserEvent2(new UserEvent(user));
			self.setType("Self");
			self.setStatus("unanswered");
			assignments.add(self);
			assigned.add(user);
			
			
			// Peers
			for(int peers : assessment.getPeers()) {
				SaAssignment assignment = new SaAssignment();
				assignment.setUserEvent1(new UserEvent(peers));
				assignment.setUserEvent2(new UserEvent(user));
				assignment.setType("Peer");
				assignment.setStatus("unanswered");
				assigned.add(peers);
				
				assignments.add(assignment);
			}
			
			// Supervisor
			for(int supervisor : assessment.getSupervisors()) {
				SaAssignment assignment = new SaAssignment();
				assignment.setUserEvent1(new UserEvent(supervisor));
				assignment.setUserEvent2(new UserEvent(user));
				assignment.setType("Supervisor");
				assignment.setStatus("unanswered");
				assigned.add(supervisor);
				
				assignments.add(assignment);
			}
			
			pairs.put(user, assigned);
		}
		
		tpRepository.insertAssignment(em, assignments, pairs);
	}

	public List<SaAssignment> retrieveAssignmentAssigned(int userEventID) { 
		return tpRepository.retrieveAssignmentAssigned(em, userEventID);
	}

	public SaAssignment retrieveAssignmentById(int assignmentID) {
		return tpRepository.retrieveAssignment(em, assignmentID);
	}

	public boolean insertAnswers(AnswerSA answersObj) {
		List<SaAnswer> answersList = new ArrayList<SaAnswer>();
		
		int[] categoryID = answersObj.getCategoryID();
		String[] answers = answersObj.getAnswers();
		SaAssignment assignment = new SaAssignment(answersObj.getAssignmentID());
		
		int length = answersObj.getCategoryID().length;
		for(int i=0; i<length; i++) {
			SaAnswer answer = new SaAnswer();
			answer.setSaCategory(new SaCategory(categoryID[i]));
			answer.setAnswer(answers[i]);
			answer.setSaAssignment(assignment);
			
			answersList.add(answer);
		}
		
		tpRepository.insertAnswer(em, answersList, assignment);
		
		return true;
	}
}