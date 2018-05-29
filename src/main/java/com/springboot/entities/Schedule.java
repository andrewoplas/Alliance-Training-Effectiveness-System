package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the schedule database table.
 * 
 */
@Entity
@NamedQuery(name="Schedule.findAll", query="SELECT s FROM Schedule s")
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SCHEDULE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCHEDULE_ID_GENERATOR")
	private int id;

	private String color;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Time timeEnd;

	private Time timeStart;

	//bi-directional many-to-one association to TrainingPlan
	@ManyToOne
	@JoinColumn(name="trainingPlanID")
	private TrainingPlan trainingPlan;

	public Schedule() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Time getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	public TrainingPlan getTrainingPlan() {
		return this.trainingPlan;
	}

	public void setTrainingPlan(TrainingPlan trainingPlan) {
		this.trainingPlan = trainingPlan;
	}
	
	public Date getStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear() + 1900, date.getMonth(), date.getDate(), timeStart.getHours(), timeStart.getMinutes(), timeStart.getSeconds());
		return cal.getTime();
	}
	
	public Date getEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear() + 1900, date.getMonth(), date.getDate(), timeEnd.getHours(), timeEnd.getMinutes(), timeEnd.getSeconds());
		return cal.getTime();
	}
	
	public boolean isEquals(Date firstDate, Date secondDate) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(firstDate);
		cal2.setTime(secondDate);
		boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
		              cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
		
		return sameDay;
	}

}