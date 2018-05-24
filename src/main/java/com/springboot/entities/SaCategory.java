package com.springboot.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	@Column(name="row_order")
	private int rowOrder;

	//bi-directional many-to-one association to SaCategory
	@ManyToOne
	@JoinColumn(name="parentID")
	private SaCategory saCategory;

	//bi-directional many-to-one association to SaCategory
	@OneToMany(mappedBy="saCategory")
	private List<SaCategory> saCategories;

	public SaCategory() {
		saCategories = new ArrayList<SaCategory>();
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