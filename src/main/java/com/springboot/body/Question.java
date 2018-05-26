package com.springboot.body;


/*
 * This class is a ResponseBody for Form's Question Entity
 * */

public class Question {
	
	int formID;
	int id;
	String question;
	String type;
	QuestionOption[] option;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFormID() {
		return formID;
	}
	public void setFormID(int formID) {
		this.formID = formID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public QuestionOption[] getOption() {
		return option;
	}
	public void setOption(QuestionOption[] option) {
		this.option = option;
	}
}
