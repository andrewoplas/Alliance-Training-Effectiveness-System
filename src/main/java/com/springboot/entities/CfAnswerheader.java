package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cf_answerheader database table.
 * 
 */
@Entity
@Table(name="cf_answerheader")
@NamedQuery(name="CfAnswerheader.findAll", query="SELECT c FROM CfAnswerheader c")
public class CfAnswerheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CF_ANSWERHEADER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CF_ANSWERHEADER_ID_GENERATOR")
	private int id;

	private int formId;

	//bi-directional many-to-one association to CfAnswerdetail
	@OneToMany(mappedBy="cfAnswerheader")
	private List<CfAnswerdetail> cfAnswerdetails;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	//bi-directional many-to-one association to TrainingEvent
	@ManyToOne
	@JoinColumn(name="trainingEventId")
	private TrainingEvent trainingEvent;

	public CfAnswerheader() {
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

	public List<CfAnswerdetail> getCfAnswerdetails() {
		return this.cfAnswerdetails;
	}

	public void setCfAnswerdetails(List<CfAnswerdetail> cfAnswerdetails) {
		this.cfAnswerdetails = cfAnswerdetails;
	}

	public CfAnswerdetail addCfAnswerdetail(CfAnswerdetail cfAnswerdetail) {
		getCfAnswerdetails().add(cfAnswerdetail);
		cfAnswerdetail.setCfAnswerheader(this);

		return cfAnswerdetail;
	}

	public CfAnswerdetail removeCfAnswerdetail(CfAnswerdetail cfAnswerdetail) {
		getCfAnswerdetails().remove(cfAnswerdetail);
		cfAnswerdetail.setCfAnswerheader(null);

		return cfAnswerdetail;
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