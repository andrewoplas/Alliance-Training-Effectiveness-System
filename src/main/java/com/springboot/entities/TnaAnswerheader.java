package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tna_answerheader database table.
 * 
 */
@Entity
@Table(name="tna_answerheader")
@NamedQuery(name="TnaAnswerheader.findAll", query="SELECT t FROM TnaAnswerheader t")
public class TnaAnswerheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TNA_ANSWERHEADER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TNA_ANSWERHEADER_ID_GENERATOR")
	private int id;

	//bi-directional many-to-one association to TnaAnswercomment
	@OneToMany(mappedBy="tnaAnswerheader")
	private List<TnaAnswercomment> tnaAnswercomments;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="evaluatorID")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userID")
	private User user2;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="positionID")
	private User user3;

	//bi-directional many-to-one association to TrainingEvent
	@ManyToOne
	@JoinColumn(name="trainingEventID")
	private TrainingEvent trainingEvent;

	//bi-directional many-to-one association to TnaAnswerupper
	@OneToMany(mappedBy="tnaAnswerheader")
	private List<TnaAnswerupper> tnaAnsweruppers;

	public TnaAnswerheader() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TnaAnswercomment> getTnaAnswercomments() {
		return this.tnaAnswercomments;
	}

	public void setTnaAnswercomments(List<TnaAnswercomment> tnaAnswercomments) {
		this.tnaAnswercomments = tnaAnswercomments;
	}

	public TnaAnswercomment addTnaAnswercomment(TnaAnswercomment tnaAnswercomment) {
		getTnaAnswercomments().add(tnaAnswercomment);
		tnaAnswercomment.setTnaAnswerheader(this);

		return tnaAnswercomment;
	}

	public TnaAnswercomment removeTnaAnswercomment(TnaAnswercomment tnaAnswercomment) {
		getTnaAnswercomments().remove(tnaAnswercomment);
		tnaAnswercomment.setTnaAnswerheader(null);

		return tnaAnswercomment;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public User getUser3() {
		return this.user3;
	}

	public void setUser3(User user3) {
		this.user3 = user3;
	}

	public TrainingEvent getTrainingEvent() {
		return this.trainingEvent;
	}

	public void setTrainingEvent(TrainingEvent trainingEvent) {
		this.trainingEvent = trainingEvent;
	}

	public List<TnaAnswerupper> getTnaAnsweruppers() {
		return this.tnaAnsweruppers;
	}

	public void setTnaAnsweruppers(List<TnaAnswerupper> tnaAnsweruppers) {
		this.tnaAnsweruppers = tnaAnsweruppers;
	}

	public TnaAnswerupper addTnaAnswerupper(TnaAnswerupper tnaAnswerupper) {
		getTnaAnsweruppers().add(tnaAnswerupper);
		tnaAnswerupper.setTnaAnswerheader(this);

		return tnaAnswerupper;
	}

	public TnaAnswerupper removeTnaAnswerupper(TnaAnswerupper tnaAnswerupper) {
		getTnaAnsweruppers().remove(tnaAnswerupper);
		tnaAnswerupper.setTnaAnswerheader(null);

		return tnaAnswerupper;
	}

}