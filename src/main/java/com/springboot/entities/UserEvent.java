package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@SequenceGenerator(name="USER_EVENT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_EVENT_ID_GENERATOR")
	private int id;

	private String role;

	private String status;

	//bi-directional many-to-one association to SaAssignment
	@OneToMany(mappedBy="userEvent1")
	private List<SaAssignment> saAssignments1;

	//bi-directional many-to-one association to SaAssignment
	@OneToMany(mappedBy="userEvent2")
	private List<SaAssignment> saAssignments2;

	//bi-directional many-to-one association to TrainingPlan
	@ManyToOne
	@JoinColumn(name="trainingPlanID")
	private TrainingPlan trainingPlan;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;

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

	public List<SaAssignment> getSaAssignments1() {
		return this.saAssignments1;
	}

	public void setSaAssignments1(List<SaAssignment> saAssignments1) {
		this.saAssignments1 = saAssignments1;
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

	
}