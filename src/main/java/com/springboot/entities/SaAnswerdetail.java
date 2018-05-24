package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sa_answerdetail database table.
 * 
 */
@Entity
@Table(name="sa_answerdetail")
@NamedQuery(name="SaAnswerdetail.findAll", query="SELECT s FROM SaAnswerdetail s")
public class SaAnswerdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SA_ANSWERDETAIL_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SA_ANSWERDETAIL_ID_GENERATOR")
	private int id;

	private String answer;

	//bi-directional many-to-one association to SaAnswerheader
	@ManyToOne
	@JoinColumn(name="answerHeaderId")
	private SaAnswerheader saAnswerheader;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="questionsId")
	private Question question;

	public SaAnswerdetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public SaAnswerheader getSaAnswerheader() {
		return this.saAnswerheader;
	}

	public void setSaAnswerheader(SaAnswerheader saAnswerheader) {
		this.saAnswerheader = saAnswerheader;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}