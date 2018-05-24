package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the training_event database table.
 * 
 */
@Entity
@Table(name="training_event")
@NamedQuery(name="TrainingEvent.findAll", query="SELECT t FROM TrainingEvent t")
public class TrainingEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRAINING_EVENT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRAINING_EVENT_ID_GENERATOR")
	private int id;

	private String role;

	private String status;

	//bi-directional many-to-one association to Attendance
	@OneToMany(mappedBy="trainingEvent")
	private List<Attendance> attendances;

	//bi-directional many-to-one association to CfAnswerheader
	@OneToMany(mappedBy="trainingEvent")
	private List<CfAnswerheader> cfAnswerheaders;

	//bi-directional many-to-one association to FfAnswerheader
	@OneToMany(mappedBy="trainingEvent1")
	private List<FfAnswerheader> ffAnswerheaders1;

	//bi-directional many-to-one association to FfAnswerheader
	@OneToMany(mappedBy="trainingEvent2")
	private List<FfAnswerheader> ffAnswerheaders2;

	//bi-directional many-to-one association to TeaAnswerheader
	@OneToMany(mappedBy="trainingEvent")
	private List<TeaAnswerheader> teaAnswerheaders;

	//bi-directional many-to-one association to TnaAnswerheader
	@OneToMany(mappedBy="trainingEvent")
	private List<TnaAnswerheader> tnaAnswerheaders;

	//bi-directional many-to-one association to TrainingPlan
	@ManyToOne
	@JoinColumn(name="trainingPlanID")
	private TrainingPlan trainingPlan;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;

	public TrainingEvent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
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
		attendance.setTrainingEvent(this);

		return attendance;
	}

	public Attendance removeAttendance(Attendance attendance) {
		getAttendances().remove(attendance);
		attendance.setTrainingEvent(null);

		return attendance;
	}

	public List<CfAnswerheader> getCfAnswerheaders() {
		return this.cfAnswerheaders;
	}

	public void setCfAnswerheaders(List<CfAnswerheader> cfAnswerheaders) {
		this.cfAnswerheaders = cfAnswerheaders;
	}

	public CfAnswerheader addCfAnswerheader(CfAnswerheader cfAnswerheader) {
		getCfAnswerheaders().add(cfAnswerheader);
		cfAnswerheader.setTrainingEvent(this);

		return cfAnswerheader;
	}

	public CfAnswerheader removeCfAnswerheader(CfAnswerheader cfAnswerheader) {
		getCfAnswerheaders().remove(cfAnswerheader);
		cfAnswerheader.setTrainingEvent(null);

		return cfAnswerheader;
	}

	public List<FfAnswerheader> getFfAnswerheaders1() {
		return this.ffAnswerheaders1;
	}

	public void setFfAnswerheaders1(List<FfAnswerheader> ffAnswerheaders1) {
		this.ffAnswerheaders1 = ffAnswerheaders1;
	}

	public FfAnswerheader addFfAnswerheaders1(FfAnswerheader ffAnswerheaders1) {
		getFfAnswerheaders1().add(ffAnswerheaders1);
		ffAnswerheaders1.setTrainingEvent1(this);

		return ffAnswerheaders1;
	}

	public FfAnswerheader removeFfAnswerheaders1(FfAnswerheader ffAnswerheaders1) {
		getFfAnswerheaders1().remove(ffAnswerheaders1);
		ffAnswerheaders1.setTrainingEvent1(null);

		return ffAnswerheaders1;
	}

	public List<FfAnswerheader> getFfAnswerheaders2() {
		return this.ffAnswerheaders2;
	}

	public void setFfAnswerheaders2(List<FfAnswerheader> ffAnswerheaders2) {
		this.ffAnswerheaders2 = ffAnswerheaders2;
	}

	public FfAnswerheader addFfAnswerheaders2(FfAnswerheader ffAnswerheaders2) {
		getFfAnswerheaders2().add(ffAnswerheaders2);
		ffAnswerheaders2.setTrainingEvent2(this);

		return ffAnswerheaders2;
	}

	public FfAnswerheader removeFfAnswerheaders2(FfAnswerheader ffAnswerheaders2) {
		getFfAnswerheaders2().remove(ffAnswerheaders2);
		ffAnswerheaders2.setTrainingEvent2(null);

		return ffAnswerheaders2;
	}

	public List<TeaAnswerheader> getTeaAnswerheaders() {
		return this.teaAnswerheaders;
	}

	public void setTeaAnswerheaders(List<TeaAnswerheader> teaAnswerheaders) {
		this.teaAnswerheaders = teaAnswerheaders;
	}

	public TeaAnswerheader addTeaAnswerheader(TeaAnswerheader teaAnswerheader) {
		getTeaAnswerheaders().add(teaAnswerheader);
		teaAnswerheader.setTrainingEvent(this);

		return teaAnswerheader;
	}

	public TeaAnswerheader removeTeaAnswerheader(TeaAnswerheader teaAnswerheader) {
		getTeaAnswerheaders().remove(teaAnswerheader);
		teaAnswerheader.setTrainingEvent(null);

		return teaAnswerheader;
	}

	public List<TnaAnswerheader> getTnaAnswerheaders() {
		return this.tnaAnswerheaders;
	}

	public void setTnaAnswerheaders(List<TnaAnswerheader> tnaAnswerheaders) {
		this.tnaAnswerheaders = tnaAnswerheaders;
	}

	public TnaAnswerheader addTnaAnswerheader(TnaAnswerheader tnaAnswerheader) {
		getTnaAnswerheaders().add(tnaAnswerheader);
		tnaAnswerheader.setTrainingEvent(this);

		return tnaAnswerheader;
	}

	public TnaAnswerheader removeTnaAnswerheader(TnaAnswerheader tnaAnswerheader) {
		getTnaAnswerheaders().remove(tnaAnswerheader);
		tnaAnswerheader.setTrainingEvent(null);

		return tnaAnswerheader;
	}

	public TrainingPlan getTrainingPlan() {
		return this.trainingPlan;
	}

	public void setTrainingPlan(TrainingPlan trainingPlan) {
		this.trainingPlan = trainingPlan;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}