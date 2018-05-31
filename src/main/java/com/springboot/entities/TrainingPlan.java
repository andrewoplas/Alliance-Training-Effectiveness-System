package com.springboot.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
		this(0);
	}
	
	public TrainingPlan(int id) {
		this.id = id;
		
		attendances = new ArrayList<Attendance>();
		schedules = new ArrayList<Schedule>();
		userEvents = new ArrayList<UserEvent>();
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
		Collections.sort(this.schedules, new Comparator<Schedule>() {
		    @Override
		    public int compare(Schedule lhs, Schedule rhs) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		        return lhs.getDate().before(rhs.getDate()) ? -1 : (lhs.getDate().after(rhs.getDate())) ? 1 : 0;
		    }
		});
		
		return this.schedules;
	}
	
	public Schedule getStartSchedule() {
		return getSchedules().get(0);
	}
	
	public Schedule getEndSchedule() {
		return getSchedules().get(schedules.size() - 1);
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
	
	public String getStatus() {
		String status = null;

		List<Schedule> sortedSchedule = getSchedules();
		Date dateStart = sortedSchedule.get(0).getStartDate();
		Date dateEnd = sortedSchedule.get(sortedSchedule.size() - 1).getEndDate();
		Date now = new Date();
			
		if (now.after(dateEnd)) {
            status = "Accomplished";
        } else if (now.before(dateStart)) {
            status = "Incoming";
        } else if (now.equals(dateStart) || now.equals(dateEnd)) {
        	status = "Ongoing";
        } else {
        	status = "Ongoing";
        }
		
		return status;
	}

}