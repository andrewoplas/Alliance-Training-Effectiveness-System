package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sa_category database table.
 * 
 */
@Entity
@Table(name="sa_category")
@NamedQuery(name="SaCategory.findAll", query="SELECT s FROM SaCategory s")
public class SaCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SA_CATEGORY_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SA_CATEGORY_ID_GENERATOR")
	private int id;

	private String description;

	@Column(name="row_order")
	private int rowOrder;

	//bi-directional many-to-one association to SaAnswer
	@OneToMany(mappedBy="saCategory")
	private List<SaAnswer> saAnswers;

	//bi-directional many-to-one association to SaCategory
	@ManyToOne
	@JoinColumn(name="parentID")
	private SaCategory saCategory;

	//bi-directional many-to-one association to SaCategory
	@OneToMany(mappedBy="saCategory")
	private List<SaCategory> saCategories;

	public SaCategory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRowOrder() {
		return this.rowOrder;
	}

	public void setRowOrder(int rowOrder) {
		this.rowOrder = rowOrder;
	}

	public List<SaAnswer> getSaAnswers() {
		return this.saAnswers;
	}

	public void setSaAnswers(List<SaAnswer> saAnswers) {
		this.saAnswers = saAnswers;
	}

	public SaAnswer addSaAnswer(SaAnswer saAnswer) {
		getSaAnswers().add(saAnswer);
		saAnswer.setSaCategory(this);

		return saAnswer;
	}

	public SaAnswer removeSaAnswer(SaAnswer saAnswer) {
		getSaAnswers().remove(saAnswer);
		saAnswer.setSaCategory(null);

		return saAnswer;
	}

	public SaCategory getSaCategory() {
		return this.saCategory;
	}

	public void setSaCategory(SaCategory saCategory) {
		this.saCategory = saCategory;
	}

	public List<SaCategory> getSaCategories() {
		return this.saCategories;
	}

	public void setSaCategories(List<SaCategory> saCategories) {
		this.saCategories = saCategories;
	}

	public SaCategory addSaCategory(SaCategory saCategory) {
		getSaCategories().add(saCategory);
		saCategory.setSaCategory(this);

		return saCategory;
	}

	public SaCategory removeSaCategory(SaCategory saCategory) {
		getSaCategories().remove(saCategory);
		saCategory.setSaCategory(null);

		return saCategory;
	}

}