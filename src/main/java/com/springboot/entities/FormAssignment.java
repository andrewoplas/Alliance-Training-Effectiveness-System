package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the form_assignment database table.
 * 
 */
@Entity
@Table(name="form_assignment")
@NamedQuery(name="FormAssignment.findAll", query="SELECT f FROM FormAssignment f")
public class FormAssignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String status;
	
	//bi-directional many-to-one association to Form
	@ManyToOne
	@JoinColumn(name="formID")
	private Form form;

	//bi-directional many-to-one association to UserEvent
	@ManyToOne
	@JoinColumn(name="userEventID")
	private UserEvent userEvent;

	//bi-directional many-to-one association to FormAnswer
	@OneToMany(mappedBy="formAssignment")
	private List<FormAnswer> formAnswers;

	public FormAssignment() {
		this(0);
	}
	
	public FormAssignment(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserEvent getUserEvent() {
		return this.userEvent;
	}

	public void setUserEvent(UserEvent userEvent) {
		this.userEvent = userEvent;
	}

	public List<FormAnswer> getFormAnswers() {
		return this.formAnswers;
	}

	public void setFormAnswers(List<FormAnswer> formAnswers) {
		this.formAnswers = formAnswers;
	}

	public FormAnswer addFormAnswer(FormAnswer formAnswer) {
		getFormAnswers().add(formAnswer);
		formAnswer.setFormAssignment(this);

		return formAnswer;
	}

	public FormAnswer removeFormAnswer(FormAnswer formAnswer) {
		getFormAnswers().remove(formAnswer);
		formAnswer.setFormAssignment(null);

		return formAnswer;
	}

}