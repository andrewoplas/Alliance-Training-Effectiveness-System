package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the participant database table.
 * 
 */
@Entity
@Table(name="participant")
@NamedQuery(name="Participant.findAll", query="SELECT p FROM Participant p")
public class Participant implements Serializable {
	private static final long serialVersionUID = 1L;

	private String role;

	//bi-directional many-to-one association to Trainingplan
	@ManyToOne
	@JoinColumn(name="tid")
	private Trainingplan trainingplan;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uid")
	private User user;

	public Participant() {
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Trainingplan getTrainingplan() {
		return this.trainingplan;
	}

	public void setTrainingplan(Trainingplan trainingplan) {
		this.trainingplan = trainingplan;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}