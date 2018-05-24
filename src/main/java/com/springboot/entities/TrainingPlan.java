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

	//bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy="trainingPlan")
	private List<Schedule> schedules;

	//bi-directional many-to-one association to TrainingEvent
	@OneToMany(mappedBy="trainingPlan")
	private List<TrainingEvent> trainingEvents;
	
	@OneToMany(mappedBy="trainingPlan")
	private List<UserEvent> userEvents;

	public TrainingPlan() {
	}
	
	public TrainingPlan(int id) {
		this.id = id;
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

	public List<TrainingEvent> getTrainingEvents() {
		return this.trainingEvents;
	}

	public void setTrainingEvents(List<TrainingEvent> trainingEvents) {
		this.trainingEvents = trainingEvents;
	}

	public TrainingEvent addTrainingEvent(TrainingEvent trainingEvent) {
		getTrainingEvents().add(trainingEvent);
		trainingEvent.setTrainingPlan(this);

		return trainingEvent;
	}

	public TrainingEvent removeTrainingEvent(TrainingEvent trainingEvent) {
		getTrainingEvents().remove(trainingEvent);
		trainingEvent.setTrainingPlan(null);

		return trainingEvent;
	}

	public List<UserEvent> getUserEvents() {
		return this.userEvents;
	}
	
	public void setUserEvents(List<UserEvent> userEvents) {
		this.userEvents = userEvents;
	}

}