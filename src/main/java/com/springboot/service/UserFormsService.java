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
		
		formsRepository.insertAssignment(em, assignments, pairs);
	}

	public List<SaAssignment> retrieveAssignmentAssigned(int userEventID) { 
		return formsRepository.retrieveAssignmentAssigned(em, userEventID);
	}

	public SaAssignment retrieveAssignmentById(int assignmentID) {
		return formsRepository.retrieveAssignment(em, assignmentID);
	}

	public boolean insertAnswers(AnswerSA answersObj) {
		// Convert JSON Object to FormAnswers Entity
		int[] categoryID = answersObj.getCategoryID();
		String[] answers = answersObj.getAnswers();
		SaAssignment assignment = new SaAssignment(answersObj.getAssignmentID());
		
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
	}

	public FormAssignment retrieveFormAssignmentById(int assignmentID) {
		return formsRepository.retrieveFormAssignmentById(em, assignmentID);
	}

	public void insertAnswers(Answer[] answers, int assignmentID) {
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
		formsRepository.updateAssignmentToAnswered(em, assignmentID);
	}
}