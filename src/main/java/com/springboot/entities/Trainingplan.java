package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the trainingplan database table.
 * 
 */
@Entity
@Table(name="trainingplan")
@NamedQuery(name="Trainingplan.findAll", query="SELECT t FROM Trainingplan t")
public class Trainingplan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	private int sid;

	private String title;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="trainingplan")
	private List<Participant> participants;

	public Trainingplan() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Participant> getParticipants() {
		return this.participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Participant addParticipant(Participant participant) {
		getParticipants().add(participant);
		participant.setTrainingplan(this);

		return participant;
	}

	public Participant removeParticipant(Participant participant) {
		getParticipants().remove(participant);
		participant.setTrainingplan(null);

		return participant;
	}

}