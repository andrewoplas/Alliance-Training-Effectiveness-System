package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ff_answerdetail database table.
 * 
 */
@Entity
@Table(name="ff_answerdetail")
@NamedQuery(name="FfAnswerdetail.findAll", query="SELECT f FROM FfAnswerdetail f")
public class FfAnswerdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FF_ANSWERDETAIL_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FF_ANSWERDETAIL_ID_GENERATOR")
	private int id;

	private String answer;

	//bi-directional many-to-one association to FfAnswerheader
	@ManyToOne
	@JoinColumn(name="answerHeaderId")
	private FfAnswerheader ffAnswerheader;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="questionId")
	private Question question;

	public FfAnswerdetail() {
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

	public FfAnswerheader getFfAnswerheader() {
		return this.ffAnswerheader;
	}

	public void setFfAnswerheader(FfAnswerheader ffAnswerheader) {
		this.ffAnswerheader = ffAnswerheader;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}