package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tna_answercomment database table.
 * 
 */
@Entity
@Table(name="tna_answercomment")
@NamedQuery(name="TnaAnswercomment.findAll", query="SELECT t FROM TnaAnswercomment t")
public class TnaAnswercomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TNA_ANSWERCOMMENT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TNA_ANSWERCOMMENT_ID_GENERATOR")
	private int id;

	private String answer;

	//bi-directional many-to-one association to TnaAnswerheader
	@ManyToOne
	@JoinColumn(name="tnaAnswerHeaderID")
	private TnaAnswerheader tnaAnswerheader;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="questionID")
	private Question question;

	public TnaAnswercomment() {
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

	public TnaAnswerheader getTnaAnswerheader() {
		return this.tnaAnswerheader;
	}

	public void setTnaAnswerheader(TnaAnswerheader tnaAnswerheader) {
		this.tnaAnswerheader = tnaAnswerheader;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}