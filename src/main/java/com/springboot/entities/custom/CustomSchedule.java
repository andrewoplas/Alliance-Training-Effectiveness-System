package com.springboot.entities.custom;

import java.util.ArrayList;
import java.util.List;


/**
 * Custom Class for Schedule
 * 
 */

public class CustomSchedule{
	private int id;

	private String title;

	private String date;

	private String timeEnd;

	private String timeStart;

	private String className;
	

	public CustomSchedule() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTimeEnd() {
		return timeEnd;
	}


	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}


	public String getTimeStart() {
		return timeStart;
	}


	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}

	
}