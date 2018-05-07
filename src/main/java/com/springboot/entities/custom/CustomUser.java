package com.springboot.entities.custom;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
public class CustomUser{
	private int id;

	private String email;

	private int isAdmin;

	private String name;

	private String password;

	private String position;

	private String status;
	
	private List<String> training;
	
	private List<String> role;

	public CustomUser() {
		training = new ArrayList<String>();
		role = new ArrayList<String>();
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

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getTraining() {
		return this.training;
	}

	public void setTraining(String training) {
		this.training.add(training);
	}
	
	public List<String> getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role.add(role);
	}
}