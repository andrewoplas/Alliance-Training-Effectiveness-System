package com.springboot.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the trainingplan database table.
 * 
 */
@Entity
@NamedQuery(name="Trainingplan.findAll", query="SELECT t FROM Trainingplan t")
public class Trainingplan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	private int sid;

	private String title;

	public Trainingplan() {
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

	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}