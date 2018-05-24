package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ff_answerheader database table.
 * 
 */
@Entity
@Table(name="ff_answerheader")
@NamedQuery(name="FfAnswerheader.findAll", query="SELECT f FROM FfAnswerheader f")
public class FfAnswerheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FF_ANSWERHEADER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FF_ANSWERHEADER_ID_GENERATOR")
	private int id;

	private int formId;

	//bi-directional many-to-one association to FfAnswerdetail
	@OneToMany(mappedBy="ffAnswerheader")
	private List<FfAnswerdetail> ffAnswerdetails;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	//bi-directional many-to-one association to TrainingEvent
	@ManyToOne
	@JoinColumn(name="trainingEventId")
	private TrainingEvent trainingEvent1;

	//bi-directional many-to-one association to TrainingEvent
	@ManyToOne
	@JoinColumn(name="facilitatorId")
	private TrainingEvent trainingEvent2;

	public FfAnswerheader() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFormId() {
		return this.formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public List<FfAnswerdetail> getFfAnswerdetails() {
		return this.ffAnswerdetails;
	}

	public void setFfAnswerdetails(List<FfAnswerdetail> ffAnswerdetails) {
		this.ffAnswerdetails = ffAnswerdetails;
	}

	public FfAnswerdetail addFfAnswerdetail(FfAnswerdetail ffAnswerdetail) {
		getFfAnswerdetails().add(ffAnswerdetail);
		ffAnswerdetail.setFfAnswerheader(this);

		return ffAnswerdetail;
	}

	public FfAnswerdetail removeFfAnswerdetail(FfAnswerdetail ffAnswerdetail) {
		getFfAnswerdetails().remove(ffAnswerdetail);
		ffAnswerdetail.setFfAnswerheader(null);

		return ffAnswerdetail;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TrainingEvent getTrainingEvent1() {
		return this.trainingEvent1;
	}

	public void setTrainingEvent1(TrainingEvent trainingEvent1) {
		this.trainingEvent1 = trainingEvent1;
	}

	public TrainingEvent getTrainingEvent2() {
		return this.trainingEvent2;
	}

	public void setTrainingEvent2(TrainingEvent trainingEvent2) {
		this.trainingEvent2 = trainingEvent2;
	}

}