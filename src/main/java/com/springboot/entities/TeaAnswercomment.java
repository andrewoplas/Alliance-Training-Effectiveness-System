package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tea_answercomment database table.
 * 
 */
@Entity
@Table(name="tea_answercomment")
@NamedQuery(name="TeaAnswercomment.findAll", query="SELECT t FROM TeaAnswercomment t")
public class TeaAnswercomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TEA_ANSWERCOMMENT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEA_ANSWERCOMMENT_ID_GENERATOR")
	private int id;

	private String answer;

	private int teaAnswerHeaderID;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="questionId")
	private Question question;

	public TeaAnswercomment() {
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

	public int getTeaAnswerHeaderID() {
		return this.teaAnswerHeaderID;
	}

	public void setTeaAnswerHeaderID(int teaAnswerHeaderID) {
		this.teaAnswerHeaderID = teaAnswerHeaderID;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}