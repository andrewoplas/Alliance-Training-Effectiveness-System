package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tea_answerheader database table.
 * 
 */
@Entity
@Table(name="tea_answerheader")
@NamedQuery(name="TeaAnswerheader.findAll", query="SELECT t FROM TeaAnswerheader t")
public class TeaAnswerheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TEA_ANSWERHEADER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEA_ANSWERHEADER_ID_GENERATOR")
	private int id;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;

	//bi-directional many-to-one association to TrainingEvent
	@ManyToOne
	@JoinColumn(name="trainingEventID")
	private TrainingEvent trainingEvent;

	public TeaAnswerheader() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TrainingEvent getTrainingEvent() {
		return this.trainingEvent;
	}

	public void setTrainingEvent(TrainingEvent trainingEvent) {
		this.trainingEvent = trainingEvent;
	}

}