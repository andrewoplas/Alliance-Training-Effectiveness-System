package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the questions database table.
 * 
 */
@Entity
@Table(name="questions")
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="QUESTIONS_QUESTIONID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUESTIONS_QUESTIONID_GENERATOR")
	@Column(name="question_id")
	private String questionId;

	private String description;

	//bi-directional many-to-one association to CfAnswerdetail
	@OneToMany(mappedBy="question")
	private List<CfAnswerdetail> cfAnswerdetails;

	//bi-directional many-to-one association to FfAnswerdetail
	@OneToMany(mappedBy="question")
	private List<FfAnswerdetail> ffAnswerdetails;

	//bi-directional many-to-one association to SaCategory
	@ManyToOne
	@JoinColumn(name="category_id")
	private SaCategory saCategory;

	//bi-directional many-to-one association to SaAnswerdetail
	@OneToMany(mappedBy="question")
	private List<SaAnswerdetail> saAnswerdetails;

	//bi-directional many-to-one association to TeaAnswercomment
	@OneToMany(mappedBy="question")
	private List<TeaAnswercomment> teaAnswercomments;

	//bi-directional many-to-one association to TnaAnswercomment
	@OneToMany(mappedBy="question")
	private List<TnaAnswercomment> tnaAnswercomments;

	public Question() {
	}

	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CfAnswerdetail> getCfAnswerdetails() {
		return this.cfAnswerdetails;
	}

	public void setCfAnswerdetails(List<CfAnswerdetail> cfAnswerdetails) {
		this.cfAnswerdetails = cfAnswerdetails;
	}

	public CfAnswerdetail addCfAnswerdetail(CfAnswerdetail cfAnswerdetail) {
		getCfAnswerdetails().add(cfAnswerdetail);
		cfAnswerdetail.setQuestion(this);

		return cfAnswerdetail;
	}

	public CfAnswerdetail removeCfAnswerdetail(CfAnswerdetail cfAnswerdetail) {
		getCfAnswerdetails().remove(cfAnswerdetail);
		cfAnswerdetail.setQuestion(null);

		return cfAnswerdetail;
	}

	public List<FfAnswerdetail> getFfAnswerdetails() {
		return this.ffAnswerdetails;
	}

	public void setFfAnswerdetails(List<FfAnswerdetail> ffAnswerdetails) {
		this.ffAnswerdetails = ffAnswerdetails;
	}

	public FfAnswerdetail addFfAnswerdetail(FfAnswerdetail ffAnswerdetail) {
		getFfAnswerdetails().add(ffAnswerdetail);
		ffAnswerdetail.setQuestion(this);

		return ffAnswerdetail;
	}

	public FfAnswerdetail removeFfAnswerdetail(FfAnswerdetail ffAnswerdetail) {
		getFfAnswerdetails().remove(ffAnswerdetail);
		ffAnswerdetail.setQuestion(null);

		return ffAnswerdetail;
	}

	public SaCategory getSaCategory() {
		return this.saCategory;
	}

	public void setSaCategory(SaCategory saCategory) {
		this.saCategory = saCategory;
	}

	public List<SaAnswerdetail> getSaAnswerdetails() {
		return this.saAnswerdetails;
	}

	public void setSaAnswerdetails(List<SaAnswerdetail> saAnswerdetails) {
		this.saAnswerdetails = saAnswerdetails;
	}

	public SaAnswerdetail addSaAnswerdetail(SaAnswerdetail saAnswerdetail) {
		getSaAnswerdetails().add(saAnswerdetail);
		saAnswerdetail.setQuestion(this);

		return saAnswerdetail;
	}

	public SaAnswerdetail removeSaAnswerdetail(SaAnswerdetail saAnswerdetail) {
		getSaAnswerdetails().remove(saAnswerdetail);
		saAnswerdetail.setQuestion(null);

		return saAnswerdetail;
	}

	public List<TeaAnswercomment> getTeaAnswercomments() {
		return this.teaAnswercomments;
	}

	public void setTeaAnswercomments(List<TeaAnswercomment> teaAnswercomments) {
		this.teaAnswercomments = teaAnswercomments;
	}

	public TeaAnswercomment addTeaAnswercomment(TeaAnswercomment teaAnswercomment) {
		getTeaAnswercomments().add(teaAnswercomment);
		teaAnswercomment.setQuestion(this);

		return teaAnswercomment;
	}

	public TeaAnswercomment removeTeaAnswercomment(TeaAnswercomment teaAnswercomment) {
		getTeaAnswercomments().remove(teaAnswercomment);
		teaAnswercomment.setQuestion(null);

		return teaAnswercomment;
	}

	public List<TnaAnswercomment> getTnaAnswercomments() {
		return this.tnaAnswercomments;
	}

	public void setTnaAnswercomments(List<TnaAnswercomment> tnaAnswercomments) {
		this.tnaAnswercomments = tnaAnswercomments;
	}

	public TnaAnswercomment addTnaAnswercomment(TnaAnswercomment tnaAnswercomment) {
		getTnaAnswercomments().add(tnaAnswercomment);
		tnaAnswercomment.setQuestion(this);

		return tnaAnswercomment;
	}

	public TnaAnswercomment removeTnaAnswercomment(TnaAnswercomment tnaAnswercomment) {
		getTnaAnswercomments().remove(tnaAnswercomment);
		tnaAnswercomment.setQuestion(null);

		return tnaAnswercomment;
	}

}