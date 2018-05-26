package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the form_answer database table.
 * 
 */
@Entity
@Table(name="form_answer")
@NamedQuery(name="FormAnswer.findAll", query="SELECT f FROM FormAnswer f")
public class FormAnswer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to FormQuestion
	@ManyToOne
	@JoinColumn(name="questionID")
	private FormQuestion formQuestion;

	public FormAnswer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FormQuestion getFormQuestion() {
		return this.formQuestion;
	}

	public void setFormQuestion(FormQuestion formQuestion) {
		this.formQuestion = formQuestion;
	}

}