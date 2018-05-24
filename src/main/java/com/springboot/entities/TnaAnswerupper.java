package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tna_answerupper database table.
 * 
 */
@Entity
@Table(name="tna_answerupper")
@NamedQuery(name="TnaAnswerupper.findAll", query="SELECT t FROM TnaAnswerupper t")
public class TnaAnswerupper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TNA_ANSWERUPPER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TNA_ANSWERUPPER_ID_GENERATOR")
	private int id;

	private String isRequired;

	private String method;

	private String task;

	@Temporal(TemporalType.DATE)
	private Date trainingDate;

	private String trainingNeed;

	private String trainingProvider;

	//bi-directional many-to-one association to TnaAnswerheader
	@ManyToOne
	@JoinColumn(name="tnaAnswerHeaderID")
	private TnaAnswerheader tnaAnswerheader;

	public TnaAnswerupper() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsRequired() {
		return this.isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTask() {
		return this.task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getTrainingDate() {
		return this.trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public String getTrainingNeed() {
		return this.trainingNeed;
	}

	public void setTrainingNeed(String trainingNeed) {
		this.trainingNeed = trainingNeed;
	}

	public String getTrainingProvider() {
		return this.trainingProvider;
	}

	public void setTrainingProvider(String trainingProvider) {
		this.trainingProvider = trainingProvider;
	}

	public TnaAnswerheader getTnaAnswerheader() {
		return this.tnaAnswerheader;
	}

	public void setTnaAnswerheader(TnaAnswerheader tnaAnswerheader) {
		this.tnaAnswerheader = tnaAnswerheader;
	}

}