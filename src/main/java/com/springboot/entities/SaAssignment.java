package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sa_assignment database table.
 * 
 */
@Entity
@Table(name="sa_assignment")
@NamedQuery(name="SaAssignment.findAll", query="SELECT s FROM SaAssignment s")
public class SaAssignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String type;

	//bi-directional many-to-one association to UserEvent
	@ManyToOne
	@JoinColumn(name="assignedTo")
	private UserEvent userEvent1;

	//bi-directional many-to-one association to UserEvent
	@ManyToOne
	@JoinColumn(name="assigned")
	private UserEvent userEvent2;
	
	private transient int assigned;
	
	private transient int assignedTo;
	

	public SaAssignment() {
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
}