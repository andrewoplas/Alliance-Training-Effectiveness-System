package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sa_answer database table.
 * 
 */
@Entity
@Table(name="sa_answer")
@NamedQuery(name="SaAnswer.findAll", query="SELECT s FROM SaAnswer s")
public class SaAnswer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SA_ANSWER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SA_ANSWER_ID_GENERATOR")
	private int id;

	private String answer;

	//bi-directional many-to-one association to SaAssignment
	@ManyToOne
	@JoinColumn(name="assignmentID")
	private SaAssignment saAssignment;

	//bi-directional many-to-one association to SaCategory
	@ManyToOne
	@JoinColumn(name="categoryID")
	private SaCategory saCategory;

	public SaAnswer() {
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

	public SaAssignment getSaAssignment() {
		return this.saAssignment;
	}

	public void setSaAssignment(SaAssignment saAssignment) {
		this.saAssignment = saAssignment;
	}

	public SaCategory getSaCategory() {
		return this.saCategory;
	}

	public void setSaCategory(SaCategory saCategory) {
		this.saCategory = saCategory;
	}

}