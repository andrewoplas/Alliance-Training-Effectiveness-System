package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cf_answerdetail database table.
 * 
 */
@Entity
@Table(name="cf_answerdetail")
@NamedQuery(name="CfAnswerdetail.findAll", query="SELECT c FROM CfAnswerdetail c")
public class CfAnswerdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CF_ANSWERDETAIL_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CF_ANSWERDETAIL_ID_GENERATOR")
	private String id;

	private String answer;

	//bi-directional many-to-one association to CfAnswerheader
	@ManyToOne
	@JoinColumn(name="answerHeaderId")
	private CfAnswerheader cfAnswerheader;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="questionId")
	private Question question;

	public CfAnswerdetail() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public CfAnswerheader getCfAnswerheader() {
		return this.cfAnswerheader;
	}

	public void setCfAnswerheader(CfAnswerheader cfAnswerheader) {
		this.cfAnswerheader = cfAnswerheader;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}