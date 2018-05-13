package com.springboot.body;

public class Training {
	private String title;
	private String description;
	private String courseOutline;
	private EventDay[] calendar;
	private String[] supervisors;
	private String[] facilitators;
	private String[] participants;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCourseOutline() {
		return courseOutline;
	}
	public void setCourseOutline(String courseOutline) {
		this.courseOutline = courseOutline;
	}
	public EventDay[] getCalendar() {
		return calendar;
	}
	public void setCalendar(EventDay[] calendar) {
		this.calendar = calendar;
	}
	public String[] getSupervisors() {
		return supervisors;
	}
	public void setSupervisors(String[] supervisors) {
		this.supervisors = supervisors;
	}
	public String[] getFacilitators() {
		return facilitators;
	}
	public void setFacilitators(String[] facilitators) {
		this.facilitators = facilitators;
	}
	public String[] getParticipants() {
		return participants;
	}
	public void setParticipants(String[] participants) {
		this.participants = participants;
	}
}
