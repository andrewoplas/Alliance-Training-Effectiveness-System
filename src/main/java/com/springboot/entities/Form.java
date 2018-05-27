package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the form database table.
 * 
 */
@Entity
@NamedQuery(name="Form.findAll", query="SELECT f FROM Form f")
public class Form implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to FormAssignment
	@OneToMany(mappedBy="form")
	private List<FormAssignment> formAssignments;

	//bi-directional many-to-one association to FormQuestion
	@OneToMany(mappedBy="form")
	private List<FormQuestion> formQuestions;

	public Form() {
	}
	
	public Form(int id) {
		this.id = id;
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

	public List<FormAssignment> getFormAssignments() {
		return this.formAssignments;
	}

	public void setFormAssignments(List<FormAssignment> formAssignments) {
		this.formAssignments = formAssignments;
	}

	public FormAssignment addFormAssignment(FormAssignment formAssignment) {
		getFormAssignments().add(formAssignment);
		formAssignment.setForm(this);

		return formAssignment;
	}

	public FormAssignment removeFormAssignment(FormAssignment formAssignment) {
		getFormAssignments().remove(formAssignment);
		formAssignment.setForm(null);

		return formAssignment;
	}

	public List<FormQuestion> getFormQuestions() {
		return this.formQuestions;
	}

	public void setFormQuestions(List<FormQuestion> formQuestions) {
		this.formQuestions = formQuestions;
	}

	public FormQuestion addFormQuestion(FormQuestion formQuestion) {
		getFormQuestions().add(formQuestion);
		formQuestion.setForm(this);

		return formQuestion;
	}

	public FormQuestion removeFormQuestion(FormQuestion formQuestion) {
		getFormQuestions().remove(formQuestion);
		formQuestion.setForm(null);

		return formQuestion;
	}

}