package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questions database table.
 * 
 */
@Entity
@NamedQuery(name="Questions.findAll", query="SELECT q FROM Questions q")
public class Questions implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="question_id")
	private String questionId;

	@Column(name="category_id")
	private int categoryId;

	private String description;

	public Questions() {
	}

	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}