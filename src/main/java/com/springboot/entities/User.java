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
	@SequenceGenerator(name="USER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ID_GENERATOR")
	private int id;

	private String email;
	
	@OneToMany(mappedBy="user")
	private List<UserEvent> userEvents;

	@Column(name="is_admin")
	private int isAdmin;

	private String name;

	private String password;

	private String status;

	//bi-directional many-to-one association to CfAnswerheader
	@OneToMany(mappedBy="user")
	private List<CfAnswerheader> cfAnswerheaders;
	
	@OneToMany(mappedBy="user")
	private List<Attendance> attendances;

	//bi-directional many-to-one association to FfAnswerheader
	@OneToMany(mappedBy="user")
	private List<FfAnswerheader> ffAnswerheaders;

	//bi-directional many-to-one association to SaAnswerheader
	@OneToMany(mappedBy="user")
	private List<SaAnswerheader> saAnswerheaders;

	//bi-directional many-to-one association to TeaAnswerheader
	@OneToMany(mappedBy="user")
	private List<TeaAnswerheader> teaAnswerheaders;

	//bi-directional many-to-one association to TnaAnswerheader
	@OneToMany(mappedBy="user1")
	private List<TnaAnswerheader> tnaAnswerheaders1;

	//bi-directional many-to-one association to TnaAnswerheader
	@OneToMany(mappedBy="user2")
	private List<TnaAnswerheader> tnaAnswerheaders2;

	//bi-directional many-to-one association to TnaAnswerheader
	@OneToMany(mappedBy="user3")
	private List<TnaAnswerheader> tnaAnswerheaders3;

	//bi-directional many-to-one association to TrainingEvent
	@OneToMany(mappedBy="user")
	private List<TrainingEvent> trainingEvents;

	//bi-directional many-to-one association to Position
	@ManyToOne
	@JoinColumn(name="position")
	private Position position;

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

	public List<CfAnswerheader> getCfAnswerheaders() {
		return this.cfAnswerheaders;
	}

	public void setCfAnswerheaders(List<CfAnswerheader> cfAnswerheaders) {
		this.cfAnswerheaders = cfAnswerheaders;
	}

	public CfAnswerheader addCfAnswerheader(CfAnswerheader cfAnswerheader) {
		getCfAnswerheaders().add(cfAnswerheader);
		cfAnswerheader.setUser(this);

		return cfAnswerheader;
	}

	public CfAnswerheader removeCfAnswerheader(CfAnswerheader cfAnswerheader) {
		getCfAnswerheaders().remove(cfAnswerheader);
		cfAnswerheader.setUser(null);

		return cfAnswerheader;
	}

	public List<FfAnswerheader> getFfAnswerheaders() {
		return this.ffAnswerheaders;
	}

	public void setFfAnswerheaders(List<FfAnswerheader> ffAnswerheaders) {
		this.ffAnswerheaders = ffAnswerheaders;
	}

	public FfAnswerheader addFfAnswerheader(FfAnswerheader ffAnswerheader) {
		getFfAnswerheaders().add(ffAnswerheader);
		ffAnswerheader.setUser(this);

		return ffAnswerheader;
	}

	public FfAnswerheader removeFfAnswerheader(FfAnswerheader ffAnswerheader) {
		getFfAnswerheaders().remove(ffAnswerheader);
		ffAnswerheader.setUser(null);

		return ffAnswerheader;
	}

	public List<SaAnswerheader> getSaAnswerheaders() {
		return this.saAnswerheaders;
	}

	public void setSaAnswerheaders(List<SaAnswerheader> saAnswerheaders) {
		this.saAnswerheaders = saAnswerheaders;
	}

	public SaAnswerheader addSaAnswerheader(SaAnswerheader saAnswerheader) {
		getSaAnswerheaders().add(saAnswerheader);
		saAnswerheader.setUser(this);

		return saAnswerheader;
	}

	public SaAnswerheader removeSaAnswerheader(SaAnswerheader saAnswerheader) {
		getSaAnswerheaders().remove(saAnswerheader);
		saAnswerheader.setUser(null);

		return saAnswerheader;
	}

	public List<TeaAnswerheader> getTeaAnswerheaders() {
		return this.teaAnswerheaders;
	}

	public void setTeaAnswerheaders(List<TeaAnswerheader> teaAnswerheaders) {
		this.teaAnswerheaders = teaAnswerheaders;
	}

	public TeaAnswerheader addTeaAnswerheader(TeaAnswerheader teaAnswerheader) {
		getTeaAnswerheaders().add(teaAnswerheader);
		teaAnswerheader.setUser(this);

		return teaAnswerheader;
	}

	public TeaAnswerheader removeTeaAnswerheader(TeaAnswerheader teaAnswerheader) {
		getTeaAnswerheaders().remove(teaAnswerheader);
		teaAnswerheader.setUser(null);

		return teaAnswerheader;
	}

	public List<TnaAnswerheader> getTnaAnswerheaders1() {
		return this.tnaAnswerheaders1;
	}

	public void setTnaAnswerheaders1(List<TnaAnswerheader> tnaAnswerheaders1) {
		this.tnaAnswerheaders1 = tnaAnswerheaders1;
	}

	public TnaAnswerheader addTnaAnswerheaders1(TnaAnswerheader tnaAnswerheaders1) {
		getTnaAnswerheaders1().add(tnaAnswerheaders1);
		tnaAnswerheaders1.setUser1(this);

		return tnaAnswerheaders1;
	}

	public TnaAnswerheader removeTnaAnswerheaders1(TnaAnswerheader tnaAnswerheaders1) {
		getTnaAnswerheaders1().remove(tnaAnswerheaders1);
		tnaAnswerheaders1.setUser1(null);

		return tnaAnswerheaders1;
	}

	public List<TnaAnswerheader> getTnaAnswerheaders2() {
		return this.tnaAnswerheaders2;
	}

	public void setTnaAnswerheaders2(List<TnaAnswerheader> tnaAnswerheaders2) {
		this.tnaAnswerheaders2 = tnaAnswerheaders2;
	}

	public TnaAnswerheader addTnaAnswerheaders2(TnaAnswerheader tnaAnswerheaders2) {
		getTnaAnswerheaders2().add(tnaAnswerheaders2);
		tnaAnswerheaders2.setUser2(this);

		return tnaAnswerheaders2;
	}

	public TnaAnswerheader removeTnaAnswerheaders2(TnaAnswerheader tnaAnswerheaders2) {
		getTnaAnswerheaders2().remove(tnaAnswerheaders2);
		tnaAnswerheaders2.setUser2(null);

		return tnaAnswerheaders2;
	}

	public List<TnaAnswerheader> getTnaAnswerheaders3() {
		return this.tnaAnswerheaders3;
	}

	public void setTnaAnswerheaders3(List<TnaAnswerheader> tnaAnswerheaders3) {
		this.tnaAnswerheaders3 = tnaAnswerheaders3;
	}

	public TnaAnswerheader addTnaAnswerheaders3(TnaAnswerheader tnaAnswerheaders3) {
		getTnaAnswerheaders3().add(tnaAnswerheaders3);
		tnaAnswerheaders3.setUser3(this);

		return tnaAnswerheaders3;
	}

	public TnaAnswerheader removeTnaAnswerheaders3(TnaAnswerheader tnaAnswerheaders3) {
		getTnaAnswerheaders3().remove(tnaAnswerheaders3);
		tnaAnswerheaders3.setUser3(null);

		return tnaAnswerheaders3;
	}

	public List<TrainingEvent> getTrainingEvents() {
		return this.trainingEvents;
	}

	public void setTrainingEvents(List<TrainingEvent> trainingEvents) {
		this.trainingEvents = trainingEvents;
	}

	public TrainingEvent addTrainingEvent(TrainingEvent trainingEvent) {
		getTrainingEvents().add(trainingEvent);
		trainingEvent.setUser(this);

		return trainingEvent;
	}

	public TrainingEvent removeTrainingEvent(TrainingEvent trainingEvent) {
		getTrainingEvents().remove(trainingEvent);
		trainingEvent.setUser(null);

		return trainingEvent;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setUserEvents(List<UserEvent> userEvents) {
		this.userEvents = userEvents;
	}
	
	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

}