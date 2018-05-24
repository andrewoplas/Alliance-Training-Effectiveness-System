package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tea_answermc database table.
 * 
 */
@Entity
@Table(name="tea_answermc")
@NamedQuery(name="TeaAnswermc.findAll", query="SELECT t FROM TeaAnswermc t")
public class TeaAnswermc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TEA_ANSWERMC_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEA_ANSWERMC_ID_GENERATOR")
	private int id;

	private String answer;

	private String question;

	private int teaAnswerHeaderID;

	public TeaAnswermc() {
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

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getTeaAnswerHeaderID() {
		return this.teaAnswerHeaderID;
	}

	public void setTeaAnswerHeaderID(int teaAnswerHeaderID) {
		this.teaAnswerHeaderID = teaAnswerHeaderID;
	}

}