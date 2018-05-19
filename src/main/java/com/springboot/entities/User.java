package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(name="is_admin")
	private int isAdmin;

	private String name;

	private String password;

	private String status;

	//bi-directional many-to-one association to Attendance
	@OneToMany(mappedBy="user")
	private List<Attendance> attendances;

	//bi-directional many-to-one association to Position
	@ManyToOne
	@JoinColumn(name="position")
	private Position position;

	//bi-directional many-to-one association to UserEvent
	@OneToMany(mappedBy="user")
	private List<UserEvent> userEvents;

	public User() {
	}
	
	public User(int id) {
		this.id= id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Attendance> getAttendances() {
		return this.attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Attendance addAttendance(Attendance attendance) {
		getAttendances().add(attendance);
		attendance.setUser(this);

		return attendance;
	}

	public Attendance removeAttendance(Attendance attendance) {
		getAttendances().remove(attendance);
		attendance.setUser(null);

		return attendance;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<UserEvent> getUserEvents() {
		return this.userEvents;
	}

	public void setUserEvents(List<UserEvent> userEvents) {
		this.userEvents = userEvents;
	}

	public UserEvent addUserEvent(UserEvent userEvent) {
		getUserEvents().add(userEvent);
		userEvent.setUser(this);

		return userEvent;
	}

	public UserEvent removeUserEvent(UserEvent userEvent) {
		getUserEvents().remove(userEvent);
		userEvent.setUser(null);

		return userEvent;
	}

}