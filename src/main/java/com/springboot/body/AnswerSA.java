package com.springboot.body;


/*
 * This class is a ResponseBody for Skills Assessment's Answer Entity
 * */

public class AnswerSA {
	
	int[] categoryID;
	String[] answers;
	int assignmentID;
	
	public int[] getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int[] categoryID) {
		this.categoryID = categoryID;
	}
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
	public int getAssignmentID() {
		return assignmentID;
	}
	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}
}
