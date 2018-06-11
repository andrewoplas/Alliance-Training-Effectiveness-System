package com.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.body.Answer;
import com.springboot.body.AnswerSA;
import com.springboot.body.AssignmentSA;
import com.springboot.entities.FormAnswer;
import com.springboot.entities.FormAssignment;
import com.springboot.entities.FormQuestion;
import com.springboot.entities.SaAnswer;
import com.springboot.entities.SaAssignment;
import com.springboot.entities.SaCategory;
import com.springboot.entities.UserEvent;
import com.springboot.repository.custom.UserFormsRepository;

@Service
public class UserFormsService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserFormsRepository formsRepository;
	
	
	public Map<Integer, List<Integer>> retrieveAssignments(int trainingID) {
		List<SaAssignment> assignments = formsRepository.retrieveAssignments(em, trainingID);
		
		// Map the assigned User to its assignment(Users)
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

	public boolean insertAssignment(AssignmentSA[] assessments) {
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
		
		formsRepository.insertAssignment(em, assignments, pairs);
		
		return true;
	}

	public List<SaAssignment> retrieveAssignmentAssigned(String userEventID) { 
		try {
			return formsRepository.retrieveAssignmentAssigned(em, Integer.parseInt(userEventID));
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			
			return null;
		}	
	}

	public SaAssignment retrieveAssignmentById(String assignmentID) {
		try {
			return formsRepository.retrieveAssignment(em, Integer.parseInt(assignmentID));
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			
			return null;
		}
	}

	public boolean insertAnswers(AnswerSA answersObj) {
		// Convert JSON Object to FormAnswers Entity
		int[] categoryID = answersObj.getCategoryID();
		String[] answers = answersObj.getAnswers();
		SaAssignment assignment = new SaAssignment(answersObj.getAssignmentID());
		
		try {
			int length = answersObj.getCategoryID().length;
			for(int i=0; i<length; i++) {
				SaAnswer answer = new SaAnswer();
				answer.setSaCategory(new SaCategory(categoryID[i]));
				answer.setAnswer(answers[i]);
				answer.setSaAssignment(assignment);
				
				// Insert SA Form Answer
				formsRepository.insertAnswer(em, answer);
			}
			
			// Set SA assignment status to 'Answered'
			formsRepository.updateSAAssignmentToAnswered(em, answersObj.getAssignmentID());
		
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return false;
		}
	}

	public FormAssignment retrieveFormAssignmentById(String assignmentID) {
		try {
			return formsRepository.retrieveFormAssignmentById(em, Integer.parseInt(assignmentID));
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			
			return null;
		}
	}

	public boolean insertAnswers(Answer[] answers, String assignmentID) {
		try {
			// Convert JSON Object to FormAnswers Entity
			for(Answer answer : answers) {
				FormAnswer formAnswer = new FormAnswer();
				formAnswer.setDescription(answer.getDescription());
				formAnswer.setFormAssignment(new FormAssignment(answer.getAssignmentID()));
				formAnswer.setFormQuestion(new FormQuestion(answer.getQuestionID()));
				
				// Insert Form Answer
				formsRepository.insertAnswer(em, formAnswer);
			}
			
			// Set assignment status to 'Answered'
			formsRepository.updateAssignmentToAnswered(em, Integer.parseInt(assignmentID));
			
			return true;
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			
			return false;
		} catch(Exception ex) {
			ex.printStackTrace();
			
			return false;
		}
	}
}