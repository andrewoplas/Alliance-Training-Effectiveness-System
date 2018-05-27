package com.springboot.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the form_question database table.
 * 
 */
@Entity
@Table(name="form_question")
@NamedQuery(name="FormQuestion.findAll", query="SELECT f FROM FormQuestion f")
public class FormQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	
	private String description;

	//bi-directional many-to-one association to Form
	@ManyToOne
	@JoinColumn(name="formID")
	private Form form;

	private String type;

	//bi-directional many-to-one association to FormAnswer
	@OneToMany(mappedBy="formQuestion")
	private List<FormAnswer> formAnswers;
	
	//bi-directional many-to-one association to FormOption
	@OneToMany(mappedBy="formQuestion")
	private List<FormOption> formOptions;

	private transient List<Integer> formOptionIDS;

	public FormQuestion() {
		this(0);
	}
	
	public FormQuestion(int id) {
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
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FormAnswer> getFormAnswers() {
		return this.formAnswers;
	}

	public void setFormAnswers(List<FormAnswer> formAnswers) {
		this.formAnswers = formAnswers;
	}

	public FormAnswer addFormAnswer(FormAnswer formAnswer) {
		getFormAnswers().add(formAnswer);
		formAnswer.setFormQuestion(this);

		return formAnswer;
	}

	public FormAnswer removeFormAnswer(FormAnswer formAnswer) {
		getFormAnswers().remove(formAnswer);
		formAnswer.setFormQuestion(null);

		return formAnswer;
	}
	
	public List<FormOption> getFormOptions() {
		return this.formOptions;
	}

	public void setFormOptions(List<FormOption> formOptions) {
		this.formOptions = formOptions;
	}

	public FormOption addFormOption(FormOption formOption) {
		getFormOptions().add(formOption);
		formOption.setFormQuestion(this);
		formOptionIDS.add(formOption.getId());

		return formOption;
	}

	public FormOption removeFormOption(FormOption formOption) {
		getFormOptions().remove(formOption);
		formOption.setFormQuestion(null);
		
		return formOption;
	}

	public int getFormOptionsCount() {
		return this.formOptions.size();
	}
	
	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
	
	public List<Integer> getFormOptionIDS() {
		return formOptionIDS;
	}

	public void setFormOptionIDS(List<Integer> formOptionIDS) {
		this.formOptionIDS = formOptionIDS;
	}

}