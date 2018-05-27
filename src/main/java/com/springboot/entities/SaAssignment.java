package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The persistent class for the sa_assignment database table.
 * 
 */
@Entity
@Table(name = "sa_assignment")
@NamedQuery(name = "SaAssignment.findAll", query = "SELECT s FROM SaAssignment s")
public class SaAssignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

	// bi-directional many-to-one association to SaAnswer
	@OneToMany(mappedBy = "saAssignment")
	private List<SaAnswer> saAnswers;

	// bi-directional many-to-one association to UserEvent
	@ManyToOne
	@JoinColumn(name = "assigned")
	private UserEvent userEvent1;

	// bi-directional many-to-one association to UserEvent
	@ManyToOne
	@JoinColumn(name = "assignedTo")
	private UserEvent userEvent2;

	private String status;

	private transient int assigned;

	private transient int assignedTo;

	public SaAssignment() {
		saAnswers = new ArrayList<SaAnswer>();
	}

	public SaAssignment(int id) {
		saAnswers = new ArrayList<SaAnswer>();

		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SaAnswer> getSaAnswers() {
		return this.saAnswers;
	}

	public void setSaAnswers(List<SaAnswer> saAnswers) {
		this.saAnswers = saAnswers;
	}

	public SaAnswer addSaAnswer(SaAnswer saAnswer) {
		getSaAnswers().add(saAnswer);
		saAnswer.setSaAssignment(this);

		return saAnswer;
	}

	public SaAnswer removeSaAnswer(SaAnswer saAnswer) {
		getSaAnswers().remove(saAnswer);
		saAnswer.setSaAssignment(null);

		return saAnswer;
	}

	public UserEvent getUserEvent1() {
		return this.userEvent1;
	}

	public void setUserEvent1(UserEvent userEvent1) {
		this.userEvent1 = userEvent1;
	}

	public UserEvent getUserEvent2() {
		return this.userEvent2;
	}

	public void setUserEvent2(UserEvent userEvent2) {
		this.userEvent2 = userEvent2;
	}

	public int getAssigned() {
		return assigned;
	}

	public void setAssigned(int assigned) {
		this.assigned = assigned;
	}

	public int getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}