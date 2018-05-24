package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sa_answerheader database table.
 * 
 */
@Entity
@Table(name="sa_answerheader")
@NamedQuery(name="SaAnswerheader.findAll", query="SELECT s FROM SaAnswerheader s")
public class SaAnswerheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SA_ANSWERHEADER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SA_ANSWERHEADER_ID_GENERATOR")
	private int id;

	private int formId;

	private String type;

	//bi-directional many-to-one association to SaAnswerdetail
	@OneToMany(mappedBy="saAnswerheader")
	private List<SaAnswerdetail> saAnswerdetails;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public SaAnswerheader() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFormId() {
		return this.formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SaAnswerdetail> getSaAnswerdetails() {
		return this.saAnswerdetails;
	}

	public void setSaAnswerdetails(List<SaAnswerdetail> saAnswerdetails) {
		this.saAnswerdetails = saAnswerdetails;
	}

	public SaAnswerdetail addSaAnswerdetail(SaAnswerdetail saAnswerdetail) {
		getSaAnswerdetails().add(saAnswerdetail);
		saAnswerdetail.setSaAnswerheader(this);

		return saAnswerdetail;
	}

	public SaAnswerdetail removeSaAnswerdetail(SaAnswerdetail saAnswerdetail) {
		getSaAnswerdetails().remove(saAnswerdetail);
		saAnswerdetail.setSaAnswerheader(null);

		return saAnswerdetail;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}