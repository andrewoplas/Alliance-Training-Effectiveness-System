package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the form_option database table.
 * 
 */
@Entity
@Table(name="form_option")
@NamedQuery(name="FormOption.findAll", query="SELECT f FROM FormOption f")
public class FormOption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to FormQuestion
	@ManyToOne
	@JoinColumn(name="questionID")
	private FormQuestion formQuestion;

	public FormOption() {
	}
	
	public FormOption(int id) {
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

	public FormQuestion getFormQuestion() {
		return this.formQuestion;
	}

	public void setFormQuestion(FormQuestion formQuestion) {
		this.formQuestion = formQuestion;
	}

}