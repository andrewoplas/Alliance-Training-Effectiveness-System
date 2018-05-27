package com.springboot.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the user_event database table.
 * 
 */
@Entity
@Table(name="user_event")
@NamedQuery(name="UserEvent.findAll", query="SELECT u FROM UserEvent u")
public class UserEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String role;

	private String status;

	//bi-directional many-to-one association to TrainingPlan
	@ManyToOne
	@JoinColumn(name="trainingPlanID")
	private TrainingPlan trainingPlan;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;
	
	//bi-directional many-to-one association to SaAssignment
	@OneToMany(mappedBy="userEvent1")
	private List<SaAssignment> saAssignments1;

	//bi-directional many-to-one association to SaAssignment
	@OneToMany(mappedBy="userEvent2")
	private List<SaAssignment> saAssignments2;

	//bi-directional many-to-one association to FormAssignment
	@OneToMany(mappedBy="userEvent")
	private List<FormAssignment> formAssignments;

	public UserEvent() {
	}
	
	public UserEvent(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TrainingPlan getTrainingPlan() {
		return this.trainingPlan;
	}

	public void setTrainingPlan(TrainingPlan trainingPlan) {
		this.trainingPlan = trainingPlan;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<SaAssignment> getSaAssignments1() {
		return this.saAssignments1;
	}

	public void setSaAssignments1(List<SaAssignment> saAssignments1) {
		this.saAssignments1 = saAssignments1;
	}
	
	public List<SaAssignment> getSaAssignments1(int id) {
		List<SaAssignment> filtered = new ArrayList<SaAssignment>();
		for(SaAssignment assignment : saAssignments1) {
			if(assignment.getUserEvent1().getId() == id) {
				filtered.add(assignment);
			}
		}
		return filtered;
	}
	
	public List<SaAssignment> getSaAssignments1Answered(int id) {
		List<SaAssignment> filtered = new ArrayList<SaAssignment>();
		for(SaAssignment assignment : saAssignments1) {
			if(assignment.getUserEvent1().getId() == id && ("answered").equals(assignment.getStatus())) {
				filtered.add(assignment);
			}
		}
		return filtered;
	}
	
	public List<SaAssignment> getSaAssignments1Unanswered(int id) {
		List<SaAssignment> filtered = new ArrayList<SaAssignment>();
		for(SaAssignment assignment : saAssignments1) {
			if(assignment.getUserEvent1().getId() == id && ("unanswered").equals(assignment.getStatus())) {
				filtered.add(assignment);
			}
		}
		return filtered;
	}

	public SaAssignment addSaAssignments1(SaAssignment saAssignments1) {
		getSaAssignments1().add(saAssignments1);
		saAssignments1.setUserEvent1(this);

		return saAssignments1;
	}

	public SaAssignment removeSaAssignments1(SaAssignment saAssignments1) {
		getSaAssignments1().remove(saAssignments1);
		saAssignments1.setUserEvent1(null);

		return saAssignments1;
	}

	public List<SaAssignment> getSaAssignments2() {
		return this.saAssignments2;
	}
	
	public List<SaAssignment> getSaAssignments2(int id) {
		List<SaAssignment> filtered = new ArrayList<SaAssignment>();
		for(SaAssignment assignment : saAssignments2) {
			if(assignment.getUserEvent2().getId() == id) {
				filtered.add(assignment);
			}
		}
		return filtered;
	}
	
	public List<SaAssignment> getSaAssignments2Answered(int id) {
		List<SaAssignment> filtered = new ArrayList<SaAssignment>();
		for(SaAssignment assignment : saAssignments2) {
			if(assignment.getUserEvent2().getId() == id && ("answered").equals(assignment.getStatus())) {
				filtered.add(assignment);
			}
		}
		return filtered;
	}
	
	public List<SaAssignment> getSaAssignments2Unanswered(int id) {
		List<SaAssignment> filtered = new ArrayList<SaAssignment>();
		for(SaAssignment assignment : saAssignments2) {
			if(assignment.getUserEvent2().getId() == id && ("unanswered").equals(assignment.getStatus())) {
				filtered.add(assignment);
			}
		}
		return filtered;
	}

	public void setSaAssignments2(List<SaAssignment> saAssignments2) {
		this.saAssignments2 = saAssignments2;
	}

	public SaAssignment addSaAssignments2(SaAssignment saAssignments2) {
		getSaAssignments2().add(saAssignments2);
		saAssignments2.setUserEvent2(this);

		return saAssignments2;
	}

	public SaAssignment removeSaAssignments2(SaAssignment saAssignments2) {
		getSaAssignments2().remove(saAssignments2);
		saAssignments2.setUserEvent2(null);

		return saAssignments2;
	}

	public List<FormAssignment> getFormAssignments() {
		return this.formAssignments;
	}
	
	public void setFormAssignments(List<FormAssignment> formAssignments) {
		this.formAssignments = formAssignments;
	}

	public FormAssignment addFormAssignment(FormAssignment formAssignment) {
		getFormAssignments().add(formAssignment);
		formAssignment.setUserEvent(this);

		return formAssignment;
	}

	public FormAssignment removeFormAssignment(FormAssignment formAssignment) {
		getFormAssignments().remove(formAssignment);
		formAssignment.setUserEvent(null);

		return formAssignment;
	}
	

	public FormAssignment getFormAssignment(int formID) {
		for(FormAssignment formAssignment : formAssignments) {
			if(formAssignment.getForm().getId() == formID) {
				return formAssignment;
			}
		}
		
		return null;
	}
	
	public FormAssignment getCourseFeedbackAssignment() {
		for(FormAssignment formAssignment : formAssignments) {
			if(formAssignment.getForm().getDescription().contains("Course Feedback")) {
				return formAssignment;
			}
		}
		
		return null;
	}
	
	public FormAssignment getFacilitatorFeedbackAssignment() {
		for(FormAssignment formAssignment : formAssignments) {
			if(formAssignment.getForm().getDescription().contains("Facilitator's Feedback")) {
				return formAssignment;
			}
		}
		
		return null;
	}
	
	public FormAssignment getTEAAssignment() {
		for(FormAssignment formAssignment : formAssignments) {
			if(formAssignment.getForm().getDescription().contains("Training Effectiveness Assessment")) {
				return formAssignment;
			}
		}
		
		return null;
	}

}

