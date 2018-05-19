package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the training_plan database table.
 * 
 */
@Entity
@Table(name="training_plan")
@NamedQuery(name="TrainingPlan.findAll", query="SELECT t FROM TrainingPlan t")
public class TrainingPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRAINING_PLAN_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRAINING_PLAN_ID_GENERATOR")
	private int id;

	@Lob
	private String courseOutline;

	private String description;

	private String title;

	//bi-directional many-to-one association to Attendance
	@OneToMany(mappedBy="trainingPlan")
	private List<Attendance> attendances;

	//bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy="trainingPlan")
	private List<Schedule> schedules;

	//bi-directional many-to-one association to UserEvent
	@OneToMany(mappedBy="trainingPlan")
	private List<UserEvent> userEvents;

	public TrainingPlan() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseOutline() {
		return this.courseOutline;
	}

	public void setCourseOutline(String courseOutline) {
		this.courseOutline = courseOutline;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Attendance> getAttendances() {
		return this.attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Attendance addAttendance(Attendance attendance) {
		getAttendances().add(attendance);
		attendance.setTrainingPlan(this);

		return attendance;
	}

	public Attendance removeAttendance(Attendance attendance) {
		getAttendances().remove(attendance);
		attendance.setTrainingPlan(null);

		return attendance;
	}

	public List<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public Schedule addSchedule(Schedule schedule) {
		getSchedules().add(schedule);
		schedule.setTrainingPlan(this);

		return schedule;
	}

	public Schedule removeSchedule(Schedule schedule) {
		getSchedules().remove(schedule);
		schedule.setTrainingPlan(null);

		return schedule;
	}

	public List<UserEvent> getUserEvents() {
		return this.userEvents;
	}

	public void setUserEvents(List<UserEvent> userEvents) {
		this.userEvents = userEvents;
	}

	public UserEvent addUserEvent(UserEvent userEvent) {
		getUserEvents().add(userEvent);
		userEvent.setTrainingPlan(this);

		return userEvent;
	}

	public UserEvent removeUserEvent(UserEvent userEvent) {
		getUserEvents().remove(userEvent);
		userEvent.setTrainingPlan(null);

		return userEvent;
	}

}